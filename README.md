# CommerceX

<p align="center">
  <img src="docs/screenshots/logo.png" alt="CommerceX Logo" width="120"/>
</p>

<p align="center">
  <strong>Production-Grade Android E-Commerce Sample</strong>
</p>

<p align="center">
  <a href="#features">Features</a> •
  <a href="#architecture">Architecture</a> •
  <a href="#tech-stack">Tech Stack</a> •
  <a href="#getting-started">Getting Started</a> •
  <a href="#testing">Testing</a>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green?style=flat-square&logo=android" alt="Platform"/>
  <img src="https://img.shields.io/badge/Language-Kotlin-purple?style=flat-square&logo=kotlin" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-blue?style=flat-square&logo=jetpack-compose" alt="Compose"/>
  <img src="https://img.shields.io/badge/Architecture-Clean%20Architecture-orange?style=flat-square" alt="Architecture"/>
  <img src="https://img.shields.io/badge/DI-Hilt-red?style=flat-square" alt="Hilt"/>
  <img src="https://img.shields.io/badge/CI-GitHub%20Actions-black?style=flat-square&logo=github-actions" alt="CI"/>
</p>

---

## 📱 Screenshots

<p align="center">
  <img src="docs/screenshots/product_list.png" width="200" alt="Product List"/>
  <img src="docs/screenshots/product_detail.png" width="200" alt="Product Detail"/>
  <img src="docs/screenshots/cart.png" width="200" alt="Cart"/>
  <img src="docs/screenshots/search.png" width="200" alt="Search"/>
</p>

---

## ✨ Features

### Core Features
- 🛍️ **Product Catalog** — Browse products with infinite scroll (Paging 3)
- 🔍 **Search & Filter** — Find products by name or category
- 🛒 **Shopping Cart** — Add, remove, update quantities
- 👤 **User Profile** — Login and view profile
- 📴 **Offline Support** — Works without internet (cache-first)

### Technical Highlights
- 🏗️ **Multi-Module Architecture** — Scalable, team-friendly codebase
- 🚩 **Feature Toggles** — A/B testing and controlled rollout ready
- 🧪 **Comprehensive Testing** — Unit tests, Flow tests, Paging tests
- 🔄 **CI/CD Pipeline** — Automated builds and tests via GitHub Actions

---

## 🏗️ Architecture

CommerceX follows **Clean Architecture** with a **multi-module** structure:

```
:app                    → Application shell, navigation, DI
:core:network           → Retrofit, OkHttp, API services
:core:database          → Room, DAOs, entities
:core:ui                → Compose theme, shared components
:core:common            → Utils, extensions, Result wrapper
:core:featureflag       → Feature toggle system
:core:testing           → Test utilities, fakes

:domain                 → Use cases, repository interfaces, models
:data                   → Repository implementations, data sources

:feature:product        → Product list & detail screens
:feature:cart           → Cart management
:feature:search         → Search & filtering
:feature:auth           → Login & profile
:feature:checkout       → Checkout flow (feature-flagged)
```

### Data Flow

```
UI (Compose) → ViewModel → UseCase → Repository → DataSource
                                         ↓
                              ┌──────────┴──────────┐
                              ↓                     ↓
                         Room (Local)         Retrofit (Remote)
```

📖 **[Full Architecture Documentation](docs/ARCHITECTURE.md)**

---

## 🛠️ Tech Stack

| Category | Technology |
|----------|------------|
| **UI** | Jetpack Compose, Material 3 |
| **Architecture** | MVVM + Clean Architecture |
| **DI** | Hilt |
| **Async** | Coroutines, Flow |
| **Networking** | Retrofit, OkHttp, Kotlinx Serialization |
| **Database** | Room |
| **Paging** | Paging 3 + RemoteMediator |
| **Navigation** | Navigation Compose |
| **Image Loading** | Coil |
| **Testing** | JUnit 5, MockK, Turbine |
| **CI/CD** | GitHub Actions |

---

## 🚩 Feature Toggles

CommerceX includes a feature toggle system for controlled rollouts:

| Flag | Description |
|------|-------------|
| `new_checkout_flow` | Redesigned checkout experience |
| `ai_recommendations` | AI-powered product suggestions |
| `promo_banners` | Dynamic promotional banners |
| `new_product_card_ui` | Redesigned product cards |

Flags can be configured via:
- Build variants (staging vs production)
- Local config file
- Debug menu (runtime override)

📖 **[Feature Toggle Documentation](docs/FEATURES.md)**

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17
- Android SDK 34+

### Clone & Build

```bash
# Clone the repository
git clone https://github.com/yourusername/CommerceX.git
cd CommerceX

# Build the project
./gradlew assembleDebug

# Run tests
./gradlew testDebugUnitTest

# Install on device
./gradlew installDebug
```

### Project Structure

```bash
CommerceX/
├── app/                    # Main application module
├── core/                   # Core modules
│   ├── network/
│   ├── database/
│   ├── ui/
│   ├── common/
│   ├── featureflag/
│   └── testing/
├── domain/                 # Domain layer
├── data/                   # Data layer
├── feature/                # Feature modules
│   ├── product/
│   ├── cart/
│   ├── search/
│   ├── auth/
│   └── checkout/
├── build-logic/            # Convention plugins
└── docs/                   # Documentation
```

---

## 🧪 Testing

### Run Tests

```bash
# All unit tests
./gradlew testDebugUnitTest

# Specific module
./gradlew :domain:test
./gradlew :feature:product:testDebugUnitTest

# With coverage report
./gradlew koverHtmlReport
```

### Test Coverage

| Module | Coverage |
|--------|----------|
| `:domain` | >90% |
| `:data` | >80% |
| `:feature:*` | >80% |

### Testing Strategy

- **Use Cases** — Unit tests with MockK
- **ViewModels** — Flow tests with Turbine
- **Repositories** — Integration tests with fakes
- **Paging** — RemoteMediator tests with TestPager

---

## 🔌 API

This project uses [DummyJSON API](https://dummyjson.com/docs) for demonstration:

| Endpoint | Description |
|----------|-------------|
| `GET /products` | All products (paginated) |
| `GET /products/{id}` | Single product |
| `GET /products/search?q=` | Search products |
| `GET /products/categories` | All categories |
| `GET /products/category/{cat}` | Products by category |
| `GET /products?limit=&skip=` | Pagination support |
| `GET /carts/user/{userId}` | User's cart |
| `POST /carts/add` | Add to cart |
| `POST /auth/login` | Authentication (JWT) |

---

## 📊 CI/CD

GitHub Actions workflow runs on every push/PR:

```yaml
Jobs:
  ✓ Build (assembleDebug)
  ✓ Lint (lintDebug, detekt)
  ✓ Unit Tests (testDebugUnitTest)
  ✓ Coverage Report (koverXmlReport)
```

---

## 📁 Documentation

| Document | Description |
|----------|-------------|
| [PROJECT_SCOPE.md](docs/PROJECT_SCOPE.md) | Project scope and timeline |
| [ARCHITECTURE.md](docs/ARCHITECTURE.md) | Detailed architecture guide |
| [FEATURES.md](docs/FEATURES.md) | Feature toggle system |

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Salman Ashraf**

- GitHub: [@yourusername](https://github.com/yourusername)
- LinkedIn: [Your LinkedIn](https://linkedin.com/in/yourprofile)

---

<p align="center">
  <sub>Built with ❤️ for portfolio demonstration</sub>
</p>

