package com.ultimaterage.app

/**
 * Ponto de integração para um núcleo de PlayStation 2.
 *
 * Nenhum núcleo está incluído neste repositório. A integração futura deve:
 * - respeitar a licença e os avisos do emulador escolhido;
 * - fornecer bibliotecas nativas arm64-v8a;
 * - receber o acesso ao arquivo escolhido pelo Storage Access Framework;
 * - validar a edição do jogo antes de iniciar.
 */
object EmulatorBridge {
    fun launchStatus(): String =
        "O núcleo de PS2 ainda não foi integrado. O arquivo foi selecionado, mas o jogo não pode iniciar nesta versão."
}
