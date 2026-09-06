
import java.util.Scanner


open class Student(
    val studentId: String,
    val fullName: String,
    val age: Int,
    val major : String,
    val gpa: Double
) {
    override fun toString(): String {
        return "ID: %-8s | Họ tên: %-20s | Tuổi: %-3d | Ngành: %-20s | GPA: %-4.2f".format(
            studentId, fullName, age, major, gpa
        )
    }
}

class StudentManager {
    private val students: MutableList<Student> = mutableListOf(
        Student("SV001", "Vu Thi Phuong Thao", 20, "Cong nghệ thong tin", 8.5),
        Student("SV002", "Dao Tuan Hoang", 21, "Kinh te quoc te", 7.2),
        Student("SV003", "Vu Duc Thinh", 19, "Cong nghệ thong tin", 4.8),
        Student("SV004", "Vu Duc Linh", 22, "Quan tri kinh doanh", 9.1),
        Student("SV005", "Nguyen Van Hieu", 20, "Ngon ngu Anh", 6.5)
    )

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun displayAll() {
        if (students.isEmpty()) {
            println("No student found")
            return
        }
        println("\n==== DANH SÁCH SINH VIÊN ====")
        students.forEach { println(it) }
    }

    fun removeStudent(studentId: String): Boolean {
        return students.removeIf { it.studentId.equals(studentId, ignoreCase = true) }
    }

    // --- Các yêu cầu thống kê & tìm kiếm ---

    fun countGpaAboveOrEqual(threshold: Double = 8.0): Long {
        return students.count { it.gpa >= threshold }.toLong()
    }

    fun countGpaBelow(threshold: Double = 5.0): Long {
        return students.count { it.gpa < threshold }.toLong()
    }

    fun averageGpaByMajor(major: String): Double {
        val filtered = students.filter { it.major.equals(major, ignoreCase = true) }
        if (filtered.isEmpty()) return 0.0
        return filtered.map { it.gpa }.average()
    }

    fun findHighestGpa(): Student? {
        return students.maxByOrNull { it.gpa }
    }

    fun findOldestStudent(): Student? {
        return students.maxByOrNull { it.age }
    }

    fun findGpaRange(min: Double = 7.0, max: Double = 8.5): List<Student> {
        return students.filter { it.gpa in min..max }
    }

    fun findByMajor(major: String): List<Student> {
        return students.filter { it.major.equals(major, ignoreCase = true) }
    }

    fun findByNameKeyword(keyword: String): List<Student> {
        return students.filter { it.fullName.contains(keyword, ignoreCase = true) }
    }

    fun sortByGpaDesc(): List<Student> {
        return students.sortedByDescending { it.gpa }
    }

    fun getTop3Gpa(): List<Student> {
        return sortByGpaDesc().take(3)
    }

    fun sortByAge(): List<Student> {
        return students.sortedBy { it.age }
    }

    fun sortByName(): List<Student> {
        return students.sortedBy { it.fullName }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val manager = StudentManager()

    while (true) {
        println("\n========== STUDENT MANAGEMENT ==========")
        println("1. Add student (Thêm sinh viên)")
        println("2. Display all students (Hiển thị tất cả sinh viên)")
        println("3. Search & Filter students (Tìm kiếm và lọc sinh viên)")
        println("4. Calculate average GPA Tính điểm trung bình GPA)")
        println("5. Statistics & Top students (Thống kê và sinh viên xuất sắc)")
        println("6. Remove student (Xóa sinh viên)")
        println("7. Sort students (Sắp xếp sinh viên)")
        println("0. Exit")
        println("========================================")
        print("Choose: ")

        val choice = scanner.nextLine().trim()

        when (choice) {
            "1" -> {
                println("\n--- THÊM SINH VIÊN MỚI ---")
                print("Nhập Student ID: ")
                val id = scanner.nextLine().trim()
                print("Nhập Full Name: ")
                val name = scanner.nextLine().trim()
                try {
                    print("Nhập Age: ")
                    val age = scanner.nextLine().trim().toInt()
                    print("Nhập Major: ")
                    val major = scanner.nextLine().trim()
                    print("Nhập GPA: ")
                    val gpa = scanner.nextLine().trim().toDouble()

                    manager.addStudent(Student(id, name, age, major, gpa))
                    println("-> Thêm sinh viên thành công!")
                } catch (e: NumberFormatException) {
                    println("-> Lỗi: Định dạng số không hợp lệ (Tuổi/GPA)!")
                }
            }
            "2" -> manager.displayAll()
            "3" -> {
                println("\n--- TÌM KIẾM SINH VIÊN ---")
                println("a. Tìm theo một phần tên")
                println("b. Tìm tất cả sinh viên thuộc một ngành")
                println("c. Tìm sinh viên có GPA từ 7.0 đến 8.5")
                print("Chọn (a/b/c): ")
                val subChoice = scanner.nextLine().trim().lowercase()

                when (subChoice) {
                    "a" -> {
                        print("Nhập từ khóa tên: ")
                        val kw = scanner.nextLine().trim()
                        val res = manager.findByNameKeyword(kw)
                        println("-> Tìm thấy ${res.size} sinh viên:")
                        res.forEach { println(it) }
                    }
                    "b" -> {
                        print("Nhập tên ngành: ")
                        val mj = scanner.nextLine().trim()
                        val res = manager.findByMajor(mj)
                        println("-> Ngành $mj có ${res.size} sinh viên:")
                        res.forEach { println(it) }
                    }
                    "c" -> {
                        val res = manager.findGpaRange(7.0, 8.5)
                        println("-> Có ${res.size} sinh viên:")
                        res.forEach { println(it) }
                    }
                }
            }
            "4" -> {
                print("Nhập tên ngành cần tính GPA trung bình (để trống nếu tính tổng quan): ")
                val mj = scanner.nextLine().trim()
                if (mj.isNotEmpty()) {
                    val avg = manager.averageGpaByMajor(mj)
                    println("-> GPA trung bình ngành $mj: %.2f".format(avg))
                } else {
                    println("-> Vui lòng nhập tên ngành cụ thể.")
                }
            }
            "5" -> {
                println("\n--- THỐNG KÊ & NÂNG CAO ---")
                println("- Số sinh viên có GPA >= 8.0: ${manager.countGpaAboveOrEqual(8.0)}")
                println("- Số sinh viên có GPA < 5.0: ${manager.countGpaBelow(5.0)}")

                manager.findHighestGpa()?.let { println("- GPA cao nhất:\n  $it") }
                manager.findOldestStudent()?.let { println("- Lớn tuổi nhất:\n  $it") }

                println("- Top 3 sinh viên có GPA cao nhất:")
                manager.getTop3Gpa().forEach { println("  $it") }
            }
            "6" -> {
                print("Nhập Student ID cần xóa: ")
                val removeId = scanner.nextLine().trim()
                if (manager.removeStudent(removeId)) {
                    println("-> Xóa sinh viên thành công!")
                } else {
                    println("-> Không tìm thấy mã sinh viên này.")
                }
            }
            "7" -> {
                println("\n--- SẮP XẾP SINH VIÊN ---")
                println("1. Sắp xếp theo GPA giảm dần")
                println("2. Sắp xếp theo tuổi tăng dần")
                println("3. Sắp xếp theo tên")
                print("Chọn: ")
                val sChoice = scanner.nextLine().trim()

                when (sChoice) {
                    "1" -> manager.sortByGpaDesc().forEach { println(it) }
                    "2" -> manager.sortByAge().forEach { println(it) }
                    "3" -> manager.sortByName().forEach { println(it) }
                }
            }
            "0" -> {
                println("Đã đóng chương trình!")
                return
            }
            else -> println("Lựa chọn không hợp lệ, vui lòng chọn lại!")
        }
    }
}