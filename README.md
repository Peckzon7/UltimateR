# Ultimate Rage

Projeto Android ARM64 dedicado à edição original americana de **Ultimate Spider-Man (PS2)**.

- ID-alvo: `SLUS-20870`
- Interface Android nativa em Kotlin
- Abas: Início, Configurações, Mods, Texturas e Drivers
- Seleção local de imagem de jogo (`.iso`) para preparar a integração com um núcleo de PS2

## Estado real deste pacote

Este é o **projeto inicial do aplicativo**, não um emulador de PS2 completo. O núcleo de emulação não está incluído. A tela de jogo deixa clara essa etapa e contém um ponto de integração (`EmulatorBridge`) para adicionar futuramente um núcleo compatível, respeitando a licença e os avisos legais do projeto escolhido.

O pacote não contém jogo, BIOS, firmware, arquivos proprietários ou cópias de componentes de terceiros. Use apenas arquivos que você tenha direito de utilizar.

## Abrir e compilar

1. Envie todo o conteúdo deste repositório ao GitHub.
2. Abra o projeto no Android Studio.
3. Aguarde a sincronização do Gradle.
4. Compile em **Build > Build APK(s)**.

Também há uma compilação automática em `.github/workflows/android.yml`. No GitHub, abra **Actions**, execute `Android APK` e baixe o artefato `ultimate-rage-debug-apk`.

## Compatibilidade

- `minSdk`: 26 (Android 8.0)
- `targetSdk`: 35
- ABI configurada: `arm64-v8a`
- Identificação de jogo esperada: `SLUS-20870`

A identificação SLUS ajuda a reconhecer a edição, mas não garante por si só compatibilidade ou desempenho. O núcleo de PS2 ainda precisa ser integrado e testado em aparelhos reais.
