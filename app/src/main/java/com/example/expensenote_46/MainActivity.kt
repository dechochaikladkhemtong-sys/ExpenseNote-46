package com.example.expensenote_46

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var stAmount: EditText
    private lateinit var stNote: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnClear: Button
    private lateinit var tvLastItem: TextView
    private lateinit var tvTotal: TextView
    private lateinit var tvTle: TextView

    private var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //เชื่อมโยงตัวแปร
        stAmount = findViewById(R.id.etAmount)
        stNote = findViewById(R.id.etNote)
        btnAdd = findViewById(R.id.btnAdd)
        btnClear = findViewById(R.id.btnClear)
        tvLastItem = findViewById(R.id.tvLastItem)
        tvTotal = findViewById(R.id.tvtotal)


        btnAdd.setOnClickListener {
            //ดึงข้อความ
            val amountText = stAmount.text.toString().trim()
            val noteText = stNote.text.toString().trim()
            //ตรวจสอบเงื่อนไข
            if (amountText.isEmpty()) {
                Toast.makeText(this, "กรุณากรอกจำนวนเงิน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(this, "กรุณากรอกจำนวนเงินเป็นตัวเลข", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            total += amount

            val showNote = if (noteText.isNotEmpty()) noteText else "ไม่ระบุ"

            tvLastItem.text = "รายการล่าสุด : $showNote - %.2f บาท".format(amount)
            tvTotal.text = "ยอดรวมวันนี้ : %.2f บาท".format(total)

            stAmount.text.clear()
            stNote.text.clear()


        }
        btnClear.setOnClickListener {
            total = 0.0

            stAmount.text.clear()
            stNote.text.clear()

            tvLastItem.text = "รายการล่าสุด : -"
            tvTotal.text = "ยอดรวมวันนี้ 0.00"
        }



    }
}