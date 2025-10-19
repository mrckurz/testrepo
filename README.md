# Java Hello World (für GitHub Actions)

Minimalprojekt zum Erweitern deiner bestehenden CI-Pipeline.

## Struktur
```
.
├─ pom.xml
├─ src
│  ├─ main/java/com/example/hello/App.java
│  └─ test/java/com/example/hello/AppTest.java
└─ .github/workflows/ci.yml   # erweiterte Pipeline
```

## Lokal ausführen
```bash
mvn -q -DskipTests=false test
```

## Pipeline (GitHub Actions)
Die beigefügte `.github/workflows/ci.yml` erweitert dein bisheriges Hello-CI:
- checkout
- setup-java (Temurin 17 + Maven-Cache)
- Build & Tests
- Upload der Surefire-Testreports als Artefakt
