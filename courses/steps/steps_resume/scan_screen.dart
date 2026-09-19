import 'package:flutter/material.dart';
import 'package:mobile_scanner/mobile_scanner.dart';
import 'package:connectivity_plus/connectivity_plus.dart';
import '../models/trainee.dart';
import '../services/api_service.dart';
import '../services/database_service.dart';

/// L'agent a déjà choisi le type (Entry/Exit) sur le Dashboard. Ici il peut :
/// 1) scanner le QR du badge du stagiaire avec la caméra, OU
/// 2) si le QR ne fonctionne pas (badge abîmé, mauvais éclairage...), taper
///    manuellement le matricule affiché sur le badge.
/// Les deux méthodes déclenchent la même vérification et ajoutent le
/// stagiaire à la liste locale (SQLite), affichée en direct sur le Dashboard.
class ScanScreen extends StatefulWidget {
  final String sessionType; // 'entry' | 'exit'
  final VoidCallback onScanAdded;

  const ScanScreen({super.key, required this.sessionType, required this.onScanAdded});

  @override
  State<ScanScreen> createState() => _ScanScreenState();
}

class _ScanScreenState extends State<ScanScreen> {
  final _api = ApiService();
  final MobileScannerController _controller = MobileScannerController();
  final _manualCtrl = TextEditingController();
  bool _processing = false;
  bool _manualMode = false;

  Future<void> _onDetect(BarcodeCapture capture) async {
    if (_processing) return;
    final matricule = capture.barcodes.firstOrNull?.rawValue;
    if (matricule == null) return;
    await _processMatricule(matricule);
  }

  Future<void> _onManualSubmit() async {
    final matricule = _manualCtrl.text.trim();
    if (matricule.isEmpty) return;
    await _processMatricule(matricule);
    _manualCtrl.clear();
  }

  /// Logique commune : vérifie le matricule (scanné OU saisi à la main) et
  /// l'ajoute à la liste des stagiaires contrôlés.
  Future<void> _processMatricule(String matricule) async {
    setState(() => _processing = true);

    final connectivity = await Connectivity().checkConnectivity();
    final isOnline = !connectivity.contains(ConnectivityResult.none);

    Trainee? trainee;
    if (isOnline) {
      trainee = await _api.lookupTrainee(matricule);
    }
    // Hors-ligne, ou si l'appel réseau échoue : on retombe sur le miroir SQLite local.
    trainee ??= await DatabaseService.instance.findTraineeByMatricule(matricule);

    if (trainee == null) {
      _showMessage('Aucun stagiaire trouvé pour le matricule "$matricule"', error: true);
    } else {
      final added = await DatabaseService.instance.addScan(trainee);
      if (added) {
        widget.onScanAdded();
        _showMessage('${trainee.fullName} ajouté ✅');
      } else {
        _showMessage('${trainee.fullName} a déjà été scanné');
      }
    }

    await Future.delayed(const Duration(milliseconds: 600));
    if (mounted) setState(() => _processing = false);
  }

  void _showMessage(String msg, {bool error = false}) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(msg), backgroundColor: error ? Colors.red : Colors.green),
    );
  }

  @override
  void dispose() {
    _controller.dispose();
    _manualCtrl.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final typeLabel = widget.sessionType == 'entry' ? 'Entrée' : 'Sortie';
    return Scaffold(
      appBar: AppBar(
        title: const Text('Security Dashboard'),
        actions: [
          // Bascule entre scan caméra et saisie manuelle
          IconButton(
            icon: Icon(_manualMode ? Icons.qr_code_scanner : Icons.keyboard),
            tooltip: _manualMode ? 'Passer au scan caméra' : 'Saisir le matricule manuellement',
            onPressed: () => setState(() => _manualMode = !_manualMode),
          ),
        ],
      ),
      body: Column(
        children: [
          Container(
            width: double.infinity,
            color: widget.sessionType == 'entry' ? Colors.green.shade600 : Colors.orange.shade700,
            padding: const EdgeInsets.symmetric(vertical: 10),
            child: Text(
              'Mode : $typeLabel',
              textAlign: TextAlign.center,
              style: const TextStyle(color: Colors.white, fontWeight: FontWeight.w600),
            ),
          ),
          Expanded(
            child: _manualMode ? _buildManualEntry() : _buildScanner(),
          ),
        ],
      ),
    );
  }

  Widget _buildScanner() {
    return Stack(
      children: [
        MobileScanner(controller: _controller, onDetect: _onDetect),
        Center(
          child: Container(
            width: 240,
            height: 240,
            decoration: BoxDecoration(
              border: Border.all(color: Colors.white, width: 2),
              borderRadius: BorderRadius.circular(16),
            ),
          ),
        ),
        Positioned(
          bottom: 24, left: 24, right: 24,
          child: OutlinedButton.icon(
            style: OutlinedButton.styleFrom(
              backgroundColor: Colors.black54,
              foregroundColor: Colors.white,
              side: const BorderSide(color: Colors.white54),
            ),
            onPressed: () => setState(() => _manualMode = true),
            icon: const Icon(Icons.keyboard),
            label: const Text('Le QR ne fonctionne pas ? Saisir le matricule'),
          ),
        ),
        if (_processing)
          const Positioned(
            top: 24, left: 0, right: 0,
            child: Center(child: CircularProgressIndicator()),
          ),
      ],
    );
  }

  Widget _buildManualEntry() {
    return Padding(
      padding: const EdgeInsets.all(24),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(Icons.badge_outlined, size: 64, color: Colors.blueGrey.shade300),
          const SizedBox(height: 16),
          const Text('Saisie manuelle du matricule',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
          const SizedBox(height: 4),
          const Text('À utiliser si le QR code du badge ne scanne pas',
              style: TextStyle(color: Colors.grey), textAlign: TextAlign.center),
          const SizedBox(height: 24),
          TextField(
            controller: _manualCtrl,
            textCapitalization: TextCapitalization.characters,
            decoration: InputDecoration(
              labelText: 'Matricule (ex: STG-0001)',
              prefixIcon: const Icon(Icons.badge),
              border: OutlineInputBorder(borderRadius: BorderRadius.circular(12)),
            ),
            onSubmitted: (_) => _onManualSubmit(),
          ),
          const SizedBox(height: 16),
          SizedBox(
            width: double.infinity,
            height: 48,
            child: FilledButton.icon(
              onPressed: _processing ? null : _onManualSubmit,
              icon: _processing
                  ? const SizedBox(width: 18, height: 18, child: CircularProgressIndicator(strokeWidth: 2, color: Colors.white))
                  : const Icon(Icons.check),
              label: const Text('Valider'),
            ),
          ),
          const SizedBox(height: 12),
          TextButton.icon(
            onPressed: () => setState(() => _manualMode = false),
            icon: const Icon(Icons.qr_code_scanner),
            label: const Text('Revenir au scan caméra'),
          ),
        ],
      ),
    );
  }
}

extension _FirstOrNull<T> on List<T> {
  T? get firstOrNull => isEmpty ? null : first;
}
