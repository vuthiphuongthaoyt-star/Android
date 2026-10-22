package com.ute.bt_event_validation
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.ute.bt_event_validation.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoToDetail.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            if (validateForm()) {
                val name = binding.edtName.text.toString().trim()
                val email = binding.edtEmail.text.toString().trim()
                val phone = binding.edtPhone.text.toString().trim()
                val msv = binding.edtMsv.text.toString().trim()
                val className = binding.edtClass.text.toString().trim()

                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("EXTRA_NAME", name)
                    putExtra("EXTRA_EMAIL", email)
                    putExtra("EXTRA_PHONE", phone)
                    putExtra("EXTRA_MSV", msv)
                    putExtra("EXTRA_CLASS", className)
                }
                startActivity(intent)
            }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        val name = binding.edtName.text.toString().trim()
        if (name.isEmpty()) {
            binding.tilName.error = "Họ và tên không được để trống"
            isValid = false
        } else {
            binding.tilName.error = null
        }

        val email = binding.edtEmail.text.toString().trim()
        if (email.isEmpty()) {
            binding.tilEmail.error = "Email không được để trống"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Email không hợp lệ"
            isValid = false
        } else {
            binding.tilEmail.error = null
        }

        val phone = binding.edtPhone.text.toString().trim()
        if (phone.isEmpty()) {
            binding.tilPhone.error = "Số điện thoại không được để trống"
            isValid = false
        } else if (!phone.matches(Regex("^0\\d{9}$"))) {
            binding.tilPhone.error = "Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0"
            isValid = false
        } else {
            binding.tilPhone.error = null
        }

        val msv = binding.edtMsv.text.toString().trim()
        if (msv.isEmpty()) {
            binding.tilMsv.error = "Mã sinh viên không được để trống"
            isValid = false
        } else {
            binding.tilMsv.error = null
        }

        val className = binding.edtClass.text.toString().trim()
        if (className.isEmpty()) {
            binding.tilClass.error = "Lớp không được để trống"
            isValid = false
        } else {
            binding.tilClass.error = null
        }

        return isValid
    }
}