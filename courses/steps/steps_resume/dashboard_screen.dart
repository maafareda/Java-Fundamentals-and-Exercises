import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import '../models/trainee.dart';
import '../services/api_service.dart';
import '../services/database_service.dart';
import 'scan_screen.dart';
import 'trainees_screen.dart';
import 'login_screen.dart';

/// Coquille avec la barre de navigation basse (Dashboard / Scan / Trainees).
class MainNavigation extends StatefulWidget {
  const MainNavigation({super.key});

  @override
  State<MainNavigation> createState() => _MainNavigationState();
}

class _MainNavigationState extends State<MainNavigation> {
  int _index = 0;
  String _sessionType = 'entry'; // choisi sur le Dashboard, utilisé par l'écran Scan
  int _refreshTick = 0; // incrémenté à chaque nouveau scan pour rafraîchir le Dashboard

  void _bumpRefresh() => setState(() => _refreshTick++);

  @override
  Widget build(BuildContext context) {
    final screens = [
      DashboardScreen(
        sessionType: _sessionType,
        onTypeChanged: (t) => setState(() => _sessionType = t),
        refreshTick: _refreshTick,
      ),
      ScanScreen(sessionType: _sessionType, onScanAdded: _bumpRefresh),
      const TraineesScreen(),
    ];

    return Scaffold(
      body: screens[_index],
      bottomNavigationBar: NavigationBar(
        selectedIndex: _index,
        onDestinationSelected: (i) => setState(() => _index = i),
        destinations: const [
          NavigationDestination(icon: Icon(Icons.dashboard_outlined), selectedIcon: Icon(Icons.dashboard), label: 'Dashboard'),
          NavigationDestination(icon: Icon(Icons.qr_code_scanner_outlined), selectedIcon: Icon(Icons.qr_code_scanner), label: 'Scan'),
          NavigationDestination(icon: Icon(Icons.people_outline), selectedIcon: Icon(Icons.people), label: 'Trainees'),
        ],
      ),
    );
  }
}

class DashboardScreen extends StatefulWidget {
  final String sessionType;
  final ValueChanged<String> onTypeChanged;
  final int refreshTick;

  const DashboardScreen({
    super.key,
    required this.sessionType,
    required this.onTypeChanged,
    required this.refreshTick,
  });

  @override
  State<DashboardScreen> createState() => _DashboardScreenState();
}

class _DashboardScreenState extends State<DashboardScreen> {
  final _api = ApiService();
  DateTime _date = DateTime.now();
  TimeOfDay _startTime = TimeOfDay.now();
  TimeOfDay _endTime = TimeOfDay.now();
  List<Trainee> _scanned = [];
  bool _syncing = false;

  @override
  void initState() {
    super.initState();
    _loadScanned();
  }

  @override
  void didUpdateWidget(covariant DashboardScreen old) {
    super.didUpdateWidget(old);
    if (old.refreshTick != widget.refreshTick) _loadScanned();
  }

  Future<void> _loadScanned() async {
    final list = await DatabaseService.instance.getCurrentScans();
    if (mounted) setState(() => _scanned = list);
  }

  Future<void> _pickDate() async {
    final picked = await showDatePicker(
      context: context, initialDate: _date,
      firstDate: DateTime(2020), lastDate: DateTime(2100),
    );
    if (picked != null) setState(() => _date = picked);
  }

  Future<void> _pickTime(bool isStart) async {
    final picked = await showTimePicker(context: context, initialTime: isStart ? _startTime : _endTime);
    if (picked != null) {
      setState(() => isStart ? _startTime = picked : _endTime = picked);
    }
  }

  String _fmtTime(TimeOfDay t) => t.format(context);
  String _pad(int n) => n.toString().padLeft(2, '0');

  /// Envoie vers l'API : le type + la période choisis + toute la liste des
  /// stagiaires scannés. En cas de succès, la liste locale est vidée et les
  /// données sont désormais stockées dans MySQL.
  Future<void> _synchronize() async {
    if (_scanned.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Aucun stagiaire scanné à synchroniser')),
      );
      return;
    }

    setState(() => _syncing = true);
    final rows = await DatabaseService.instance.getPendingRows();
    final scans = rows
        .map((r) => {
              'trainee_matricule': r['matricule'] as String,
              'scanned_at': r['scanned_at'] as String,
            })
        .toList();

    final result = await _api.sync(
      sessionType: widget.sessionType,
      date: DateFormat('yyyy-MM-dd').format(_date),
      startTime: '${_pad(_startTime.hour)}:${_pad(_startTime.minute)}',
      endTime: '${_pad(_endTime.hour)}:${_pad(_endTime.minute)}',
      scans: scans,
    );
    setState(() => _syncing = false);

    if (result != null) {
      await DatabaseService.instance.clearSyncedScans();
      await _loadScanned();
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('${scans.length} stagiaire(s) synchronisé(s) avec la base de données ✅')),
        );
      }
    } else if (mounted) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Synchronisation impossible — vérifiez la connexion, les scans restent enregistrés localement'),
          backgroundColor: Colors.red,
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Security Dashboard'),
        actions: [
          IconButton(
            icon: const Icon(Icons.logout),
            onPressed: () async {
              await _api.logout();
              if (context.mounted) {
                Navigator.of(context).pushAndRemoveUntil(
                  MaterialPageRoute(builder: (_) => const LoginScreen()), (r) => false);
              }
            },
          ),
        ],
      ),
      body: RefreshIndicator(
        onRefresh: _loadScanned,
        child: ListView(
          padding: const EdgeInsets.all(16),
          children: [
            // --- Sélecteur Entrée / Sortie ---
            SegmentedButton<String>(
              segments: const [
                ButtonSegment(value: 'entry', label: Text('Entry'), icon: Icon(Icons.check)),
                ButtonSegment(value: 'exit', label: Text('Exit'), icon: Icon(Icons.exit_to_app)),
              ],
              selected: {widget.sessionType},
              onSelectionChanged: (s) => widget.onTypeChanged(s.first),
            ),
            const SizedBox(height: 16),

            // --- Date ---
            _SelectorTile(
              icon: Icons.calendar_today,
              title: DateFormat('EEEE, MMMM d, yyyy').format(_date),
              subtitle: 'Select Date',
              onTap: _pickDate,
            ),
            const SizedBox(height: 12),

            // --- Heures ---
            Row(
              children: [
                Expanded(
                  child: _SelectorTile(
                    icon: Icons.access_time,
                    title: _fmtTime(_startTime),
                    subtitle: 'Start Time',
                    onTap: () => _pickTime(true),
                  ),
                ),
                const SizedBox(width: 12),
                Expanded(
                  child: _SelectorTile(
                    icon: Icons.access_time,
                    title: _fmtTime(_endTime),
                    subtitle: 'End Time',
                    onTap: () => _pickTime(false),
                  ),
                ),
              ],
            ),
            const SizedBox(height: 20),

            // --- Bouton Synchroniser ---
            SizedBox(
              width: double.infinity,
              height: 48,
              child: FilledButton.icon(
                onPressed: _syncing ? null : _synchronize,
                icon: _syncing
                    ? const SizedBox(width: 18, height: 18, child: CircularProgressIndicator(strokeWidth: 2, color: Colors.white))
                    : const Icon(Icons.sync),
                label: Text(_syncing ? 'Synchronisation...' : 'Synchroniser'),
              ),
            ),
            const SizedBox(height: 20),

            // --- Stagiaires scannés (liste locale avant synchronisation) ---
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                const Text('Scanned Trainees', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16)),
                CircleAvatar(
                  radius: 12,
                  backgroundColor: Colors.redAccent,
                  child: Text('${_scanned.length}', style: const TextStyle(fontSize: 12, color: Colors.white)),
                ),
              ],
            ),
            const SizedBox(height: 12),
            if (_scanned.isEmpty)
              Padding(
                padding: const EdgeInsets.symmetric(vertical: 24),
                child: Column(
                  children: [
                    Icon(Icons.person_search, size: 48, color: Colors.grey.shade400),
                    const SizedBox(height: 8),
                    const Text('No trainees scanned yet', style: TextStyle(color: Colors.grey)),
                  ],
                ),
              )
            else
              ..._scanned.map((t) => ListTile(
                    leading: const CircleAvatar(child: Icon(Icons.person)),
                    title: Text(t.fullName),
                    subtitle: Text('${t.matricule}${t.filiere != null ? " • ${t.filiere}" : ""}'),
                    trailing: Text(t.scannedAt != null
                        ? DateFormat('HH:mm').format(DateTime.parse(t.scannedAt!))
                        : ''),
                  )),
          ],
        ),
      ),
    );
  }
}

class _SelectorTile extends StatelessWidget {
  final IconData icon;
  final String title;
  final String subtitle;
  final VoidCallback onTap;

  const _SelectorTile({required this.icon, required this.title, required this.subtitle, required this.onTap});

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: onTap,
      borderRadius: BorderRadius.circular(12),
      child: Container(
        padding: const EdgeInsets.all(14),
        decoration: BoxDecoration(
          border: Border.all(color: Colors.grey.shade300),
          borderRadius: BorderRadius.circular(12),
        ),
        child: Row(
          children: [
            Icon(icon, size: 20),
            const SizedBox(width: 10),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(title, style: const TextStyle(fontWeight: FontWeight.w600)),
                  Text(subtitle, style: const TextStyle(fontSize: 12, color: Colors.grey)),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
