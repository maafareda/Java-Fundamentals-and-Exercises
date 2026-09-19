import 'package:flutter/material.dart';
import 'package:connectivity_plus/connectivity_plus.dart';
import '../models/trainee.dart';
import '../services/api_service.dart';
import '../services/database_service.dart';

class TraineesScreen extends StatefulWidget {
  const TraineesScreen({super.key});

  @override
  State<TraineesScreen> createState() => _TraineesScreenState();
}

class _TraineesScreenState extends State<TraineesScreen> {
  final _api = ApiService();
  List<Trainee> _trainees = [];
  bool _loading = true;
  String _status = '';

  @override
  void initState() {
    super.initState();
    _load();
  }

  Future<void> _load() async {
    setState(() {
      _loading = true;
      _status = 'Syncing data...';
    });

    final connectivity = await Connectivity().checkConnectivity();
    final isOnline = !connectivity.contains(ConnectivityResult.none);

    if (isOnline) {
      // On récupère la liste à jour depuis MySQL et on rafraîchit le miroir
      // SQLite local (utilisé pour vérifier les badges même hors-ligne).
      final remote = await _api.getTrainees();
      if (remote.isNotEmpty) {
        await DatabaseService.instance.cacheTrainees(remote);
        setState(() => _trainees = remote);
      } else {
        setState(() => _trainees = []);
      }
    } else {
      // Hors-ligne : on affiche simplement le miroir SQLite
      setState(() => _status = 'Offline — showing local data');
    }

    setState(() {
      _loading = false;
      _status = '';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Security Dashboard')),
      body: Column(
        children: [
          Expanded(
            child: _loading
                ? const Center(child: CircularProgressIndicator())
                : _trainees.isEmpty
                    ? Center(
                        child: Column(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            Icon(Icons.people_outline, size: 64, color: Colors.blueGrey.shade300),
                            const SizedBox(height: 16),
                            const Text('Trainee Management',
                                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                            const SizedBox(height: 4),
                            const Text('View and manage trainees', style: TextStyle(color: Colors.grey)),
                          ],
                        ),
                      )
                    : RefreshIndicator(
                        onRefresh: _load,
                        child: ListView.separated(
                          itemCount: _trainees.length,
                          separatorBuilder: (_, __) => const Divider(height: 1),
                          itemBuilder: (context, i) {
                            final t = _trainees[i];
                            return ListTile(
                              leading: const CircleAvatar(child: Icon(Icons.person)),
                              title: Text(t.fullName),
                              subtitle: Text('${t.matricule}${t.filiere != null ? " • ${t.filiere}" : ""}'),
                            );
                          },
                        ),
                      ),
          ),
          if (_status.isNotEmpty)
            Container(
              width: double.infinity,
              color: Colors.grey.shade200,
              padding: const EdgeInsets.symmetric(vertical: 10),
              child: Text(_status, textAlign: TextAlign.center, style: const TextStyle(color: Colors.black87)),
            ),
        ],
      ),
    );
  }
}
