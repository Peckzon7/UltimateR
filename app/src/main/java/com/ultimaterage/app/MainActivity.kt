package com.ultimaterage.app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.graphics.Color
import android.graphics.Typeface

class MainActivity : Activity() {
    private lateinit var content: LinearLayout
    private var selectedGame: Uri? = null

    private val bg = Color.rgb(12, 15, 22)
    private val panel = Color.rgb(25, 31, 43)
    private val accent = Color.rgb(230, 65, 55)
    private val white = Color.WHITE
    private val muted = Color.rgb(175, 185, 200)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun shell(title: String) {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
            setPadding(20, 24, 20, 16)
        }
        val heading = TextView(this).apply {
            text = title
            textSize = 28f
            setTextColor(white)
            typeface = Typeface.DEFAULT_BOLD
        }
        root.addView(heading)
        val scroll = ScrollView(this)
        content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, 18, 0, 18)
        }
        scroll.addView(content)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))

        val nav = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        listOf("Início", "Configurações", "Mods", "Texturas", "Drivers").forEach { label ->
            val b = Button(this).apply {
                text = label
                textSize = 9f
                setOnClickListener { navigate(label) }
            }
            nav.addView(b, LinearLayout.LayoutParams(0, 52, 1f))
        }
        root.addView(nav)
        setContentView(root)
    }

    private fun navigate(label: String) {
        when (label) {
            "Início" -> showHome()
            "Configurações" -> showSettings()
            "Mods" -> showMods()
            "Texturas" -> showTextures()
            "Drivers" -> showDrivers()
        }
    }

    private fun addText(text: String, size: Float = 16f, color: Int = white, bold: Boolean = false) {
        content.addView(TextView(this).apply {
            this.text = text
            textSize = size
            setTextColor(color)
            if (bold) typeface = Typeface.DEFAULT_BOLD
            setPadding(4, 8, 4, 8)
        })
    }

    private fun addButton(label: String, action: () -> Unit) {
        content.addView(Button(this).apply {
            text = label
            setOnClickListener { action() }
        }, LinearLayout.LayoutParams(-1, -2).apply { topMargin = 8; bottomMargin = 8 })
    }

    private fun showHome() {
        shell("ULTIMATE RAGE")
        addText("ULTIMATE SPIDER-MAN • PLAYSTATION 2", 13f, muted, true)
        addText("Edição-alvo: SLUS-20870 (NTSC-U)", 16f, white, true)
        addText("Selecione uma imagem de jogo que você tenha direito de utilizar. O núcleo de PS2 ainda será integrado.", 14f, muted)
        addButton("Selecionar jogo (.ISO)") { pickGame() }
        addText(selectedGame?.lastPathSegment?.let { "Arquivo selecionado: $it" } ?: "Nenhum jogo selecionado", 13f, muted)
        addButton("Abrir jogo") {
            addText(EmulatorBridge.launchStatus(), 14f, accent, true)
        }
        addText("Status: interface inicial • emulação ainda não integrada", 12f, muted)
    }

    private fun pickGame() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
        }
        startActivityForResult(intent, 100)
    }

    @Deprecated("Use Activity Result API when extending this project")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            selectedGame = data?.data
            selectedGame?.let { contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION) }
            showHome()
        }
    }

    private fun showSettings() {
        shell("Configurações")
        addText("Perfil do dispositivo", 18f, white, true)
        addText("ABI do aplicativo: arm64-v8a", color = muted)
        addText("Resolução, velocidade, renderizador e controles serão configurados quando o núcleo de PS2 for integrado.", 14f, muted)
        addText("Não há ajustes falsos de FPS ou resolução nesta versão inicial.", 13f, accent)
    }

    private fun showMods() {
        shell("Mods")
        addText("Área preparada para mods compatíveis com SLUS-20870.", 15f)
        addText("O gerenciador de mods ainda não está ativo. A instalação exigirá validação de formato e cópias de segurança.", 13f, muted)
    }

    private fun showTextures() {
        shell("Texturas")
        addText("Área preparada para pacotes de textura da edição SLUS-20870.", 15f)
        addText("A substituição de texturas depende do suporte oferecido pelo núcleo de emulação escolhido.", 13f, muted)
    }

    private fun showDrivers() {
        shell("Drivers")
        addText("Compatibilidade gráfica", 18f, white, true)
        addText("Nenhum driver externo está incluído. A seleção de renderizador dependerá do núcleo integrado e do aparelho.", 14f, muted)
        addText("Não instale drivers aleatórios: use somente componentes compatíveis e de origem confiável.", 13f, accent)
    }
}
