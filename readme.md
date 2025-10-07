# 🌍 GreenExpedition
**Entdecke die Welt – und schütze sie.**

GreenExpedition ist eine interaktive Android-App, die Neugierde und Entdeckergeist wecken soll – besonders bei jungen Nutzer:innen.  
Die App verbindet Technik und Natur auf spielerische Weise und zeigt, wie man durch bewusstes Handeln wieder stärker in Verbindung mit der Umwelt treten kann.

Nutzer:innen können ihren CO₂-Fußabdruck berechnen, spannende Fakten über unseren Planeten entdecken und eine persönliche Sammlung von entdeckten Tier- und Pflanzenarten anlegen.

---

## 📚 Inhaltsverzeichnis
- [Ziel der App](#-ziel-der-app)
- [Features](#-features)
- [Technischer Aufbau](#-technischer-aufbau)
    - [Architektur](#🏗️-architektur)
    - [Projektaufbau](#-projektaufbau)
    - [Datenspeicherung](#-datenspeicherung)
    - [APIs](#-apis)
    - [3rd-Party Libraries](#-3rd-party-libraries)
- [Usage / Kurzanleitung](#usage--kurzanleitung)
- [Learnings & Motivation](#-learnings--motivation)
- [Ausblick / Roadmap](#-ausblick--roadmap)
- [Design / Screenshots](#-design--screenshots)
- [Beitragen](#-beitragen)
- [Lizenz & Kontakt](#-lizenz--kontakt)

---

## 🎯 Ziel der App

GreenExpedition richtet sich an junge, neugierige Nutzer:innen, die auf interaktive und positive Weise mehr über Umwelt- und Naturschutz lernen möchten.  
Die App liefert wertvolle Einblicke in ökologische Themen, macht Fortschritte sichtbar und motiviert zu eigenem, nachhaltigem Handeln.

---

## ✨ Features

- 🧭 **Interaktive Weltkarte**
    - Kontinent-Ansichten mit faktenbasierten Daten: Meeresspiegelveränderungen, Müll- und Recyclingstatistiken, Artensterben.
    - **Positive Facts**: Erfolgreiche Umweltschutzprojekte, CO₂-Verbesserungen, Fortschritte im Recycling.

- 🌿 **CO₂-Fußabdruck-Quiz**
    - Kurzes Quiz zur Abschätzung des persönlichen Fußabdrucks.
    - Individuelle Tipps zur Verbesserung.
    - **Vergleich mit anderen:** weltweit, pro Kontinent und Deutschland.

- 📸 **Artenerkennung & Sammlung**
    - Bilder hochladen → automatische Artbestimmung via API.
    - Erkannte Arten werden in einer persönlichen Sammlung gespeichert.
    - Synchronisierung mit Firebase.

- ☁️ **Firebase-Integration**
    - Speicherung & Synchronisierung der Nutzersammlung.
    - Authentifizierung & Sicherheitsregeln integriert.

- 🔁 **Offline-First**
    - Datenhaltung über Room für Offline-Nutzung.

---

## 🧩 Technischer Aufbau

### 🏗️ Architektur
- **MVVM (Model-View-ViewModel)**
- **Jetpack Compose** für UI
- **Repositories** als Schnittstelle zwischen ViewModels, Room & APIs
- **Coroutines / Flow** für asynchrone Datenverarbeitung

---

### 🗂️ Projektaufbau

**App-Struktur (Kotlin + Compose)**
- `ui/` → Composables, Screens
- `viewmodel/` → ViewModels & State-Handling
- `data/`
    - `local/` → Room, Entities, DAOs
    - `remote/` → Retrofit, API-Clients
    - `repository/` → zentrale Datenlogik
- `model/` → Datenklassen
- `util/` → Hilfsfunktionen, Mappings
- `res/` → Layouts, Farben, Icons
- `img/` → Screenshots, App-Assets

---

### 💾 Datenspeicherung
- **Room (lokal):** Quiz-Ergebnisse, Nutzerdaten, Offline-Cache
- **Firebase (cloud):** Nutzersammlungen & Bilder
- **Sicherheit:** Keine sensiblen Schlüssel im Client

---

### 🌐 APIs
- **Eigene REST-API:**
    - Aggregiert Umweltdaten (z.B. CO₂, Recycling, Artenvielfalt) weltweit und pro Kontinent
    - Enthält positive Fakten & Trends
- **Nature Identification API v2 (https://multi-source.identify.biodiversityanalysis.eu):**
    - Bestimmt Pflanzen & Tiere anhand von Bildern
    - Rückgabe: Artname, Confidence, Zusatzinfos

---

### 🧰 3rd-Party Libraries
- Kotlin, Jetpack Compose, ViewModel
- Room, Retrofit, Moshi
- Coil (Image Loading)
- Firebase SDK (Realtime DB, Storage, Auth)
- Coroutines / Flow
- Koin (Dependency Injection)

---

## ⚙️ Usage / Kurzanleitung
1. Quiz starten → CO₂-Fußabdruck berechnen.
2. Ergebnis vergleichen → weltweit, pro Kontinent, Deutschland.
3. Weltkarte erkunden → Fakten & positive Entwicklungen entdecken.
4. Foto einer Pflanze/Tiers hochladen → Art erkennen → speichern.
5. Sammlung jederzeit ansehen und erweitern.

---

## 🧠 Learnings & Motivation
Dieses Projekt verbindet Technik mit Umweltbewusstsein.  
Ziel war es, eine App zu entwickeln, die informiert, inspiriert und motiviert, sich aktiv mit der Natur auseinanderzusetzen.

**Wesentliche Learnings:**
- MVVM & Clean Architecture mit Compose
- Offline-First-Design mit Room
- Eigenes API-Konzept & Datenaggregation
- Integration externer APIs & Firebase

---

## 🔮 Ausblick / Roadmap
- [ ] Erweiterte CO₂-Analysen (Lebensstile, Altersgruppen)
- [ ] Interaktive Diagramme & Visualisierungen
- [ ] Gamification (Badges, Challenges)
- [ ] Community-Features (Beobachtungen teilen)
- [ ] iOS-Portierung (SwiftUI)

---

## 🎨 Design / Screenshots

<p>
  tbd
</p>


---

## 📜 Lizenz & Kontakt
- **Lizenz:** MIT
- **Kontakt:** Alex Jäger Peña — siehe GitHub-Profil `@AlexJaegerPena`

---

**Danke fürs Anschauen!** 🌱  