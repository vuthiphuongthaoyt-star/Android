package com.ute.bt_event_validation
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.bt_event_validation.databinding.ActivityDetailBinding
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("EXTRA_NAME") ?: "Chưa có thông tin"
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: "Chưa có thông tin"
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: "Chưa có thông tin"
        val msv = intent.getStringExtra("EXTRA_MSV") ?: "Chưa có thông tin"
        val className = intent.getStringExtra("EXTRA_CLASS") ?: "Chưa có thông tin"

        binding.tvName.text = "Họ và tên: $name"
        binding.tvEmail.text = "Email: $email"
        binding.tvPhone.text = "Số điện thoại: $phone"
        binding.tvMsv.text = "Mã sinh viên: $msv"
        binding.tvClass.text = "Lớp: $className"

        binding.btnBackToRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}