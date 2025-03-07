**REFLECTION TUTORIAL 1** 

***REFLECTION 1***

Dalam mengerjakan tutorial dan exercise 1 ini, saya telah menerapkan clean code.
1. Meaningful names, saya telah membuat nama variabel dan fungsi yang deskriptif dan jelas sesuai dengan fungsinya. Contohnya: updatedName, artinya nama product yang sudah diupdate. findById(), yaitu fungsi untuk menemukan product dari productRepository menggunakan id.
2. Function, saya membuat beberapa function baru untuk membantu sebuah method daripada membuat semua kode itu (yang ada di function baru) dalam method tersebut. Contohnya function set pada ProductRepository
3. Comment, saya membuat comment yang jelas untuk beberapa fungsi yang agak panjang (tidak dapat langsung dimengerti dari sekilas) namun saya tidak membuat comment yang tidak diperlukan untuk nama variabel yang sudah jelas dan untuk fungsi yang sudah jelas kegunaannya.

Improvement di source code:
1. Membuat variabel idIterator untuk id dari product
2. Mengubah template untuk form create product


***REFLECTION 2***
1. Seteleh mengerjakan unit test, saya menyadari ada beberapa kesalahan minor dalam kode saya. Semisal, pada saat create product baru, saya tidak mempertimbangkan jika product sudah memiliki id, sebelumnya saya langsung membuatkan id menggunakan iterator untuk product itu. Banyaknya unit test yang dibutuhkan setiap class bergantung pada berapa banyak skenario yang mungkin untuk class itu. Untuk memastikan jika unit test kita cukup, kita harus bisa mempertimbangkan semua skenario yang dapat menyebabkan kode kita gagal. Jika kita memiliki 100% code coverage, bukan berarti code kita tidak memiliki bug. Kode yang perlu diuji di unit test hanyalah kode yang perlu saja, semisal sebuah fungsi yang trivial seperti penjumlahan dua angka tidak perlu kita lakukan unit test. Karena itu, code coverage yang baik adalah yang mencakup yang diperlukan.
2. Dengan setup yang sama, maka kode yang dibuat di awalnya kurang lebih akan sama, ini akan mengganggu prinsip clean code. Untuk mengatasinya, kita dapat membuat sebuah abstract class yang akan diinherit oleh kedua class CreateProductFunctionalTest.java dan java class yang baru dibuat.



**REFLECTION TUTORIAL 3**

1. SOLID Principles yang saya terapkan pada project ini:
- SRP

Dalam kode ini, saya telah menerapkan prinsip SRP (Single Responsibility Principle), yang menyatakan bahwa setiap kelas harus menangani tanggung jawabnya masing-masing. Sebagai contoh, untuk menjaga agar setiap kelas tetap fokus pada fungsionalitas yang sesuai, saya memisahkan metode pembantu untuk menghasilkan UUID ke dalam kelas terpisah, yaitu IdGenerator.java. Sementara itu, logika bisnis utama tetap berada di kelas yang relevan. Selain itu, class lain seperti service, dan controller telah menangani tanggung jawabnya masing-masing tanpa diubah. Dengan menerapkan SRP, kode menjadi lebih terstruktur dan mudah dikelola.
- OCP

Saya telah menerapkan OCP, yang berarti kode saya dirancang agar terbuka untuk pengembangan, namun tertutup untuk modifikasi langsung. Saya menerapkan prinsip ini dengan cara membuat generic interface untuk repository, service, dan juga controller. Dimana, interace tersebut dapat diimplementasikan untuk kedua class, Product dan Car. Sehingga jika ada penambahan object baru, kita tidak perlu lagi membuat interface khusus object tersebut, namun cukup mengimplementasikan generic interface.
- LSP

Pada kode before-solid. CarController menginherit dari ProductController. Hal ini melanggar prinsip LSP dimana fungsionalitas car yang sama dengan product dapat terganggu. Sebagai contoh, pada kode before-solid, jika kita memasukkan /car/listCar (sesuai) akan menampilkan halaman list car. Namun, jika kita memasukkan /car/list, sekarang ini akan aplikasi web kita justru menampilkan halaman list product. Karena itu, saya membuat interface untuk kedua controller yang diimplementasikan kepada kedua controller dan saya menghapus inherit ProductController ke CarController. Sehingga, sekarang jika kita memasukkan /car/list. Aplikasi kita akan menampilkan halaman list car.
- ISP

Pada kode ini, tidak ada yang melanggar prinsip ISP secara langsung. Prinsip ini menyatakan bahwa sebuah interface seharusnya tidak memaksa kelas untuk mengimplementasikan metode yang tidak mereka butuhkan. Namun, saya membuat interface-interface khusus pada controller seperti CreateController, ListController, dll. Sejauh ini, interface tersebut memang tidak diterapkan karena Product dan Car mengimplementasi semuanya secara langsung (BaseController), namun sebagai antisipasi jika ada object baru yang tidak membutuhkan salah satu fungsionalitasnya, maka object itu dapat mengimplement interface yang diperlukan saja.

2. Setelah menerapkan prinsip OCP, ISP dan LSP, pengembangan kode akan menjadi lebih efisien. Sebagai contoh, jika ada banyak product yang akan kita tambahkan ke depannya, tidak perlu lagi repot repot membuat interface seperti ProductService dan CarService seperti pada before-solid. Kita juga tidak perlu repot repot untuk mengimplementasi repository dari awal karena sudah ada interface yang menyediakan template. Selain itu, dengan menerapkan prinsip SRP kode menjadi lebih clean karena setiap class menangani fungsionalitasnya masing masing seperti pada contoh yang saya berikan pada no 1.

3. Jika tidak menerapkan prinsip SOLID, pengembangan kode akan menjadi lebih sulit dan rentan terhadap perubahan yang tidak diinginkan. Setiap kali ada kebutuhan untuk menambahkan fitur baru atau objek baru, kita harus mengubah kode yang sudah ada, yang dapat meningkatkan risiko bug dan menyebabkan ketergantungan yang tidak perlu antar kelas. Misalnya, tanpa OCP, kita perlu membuat interface baru seperti ProductService dan CarService secara terpisah untuk setiap jenis produk, yang akan memperbanyak kode dan menyulitkan pemeliharaan. Selain itu, tanpa menerapkan OCP pada repository, setiap entitas baru akan memerlukan repository khusus, yang dapat menyebabkan duplikasi kode. Dengan menerapkan OCP, kita cukup menggunakan satu generic interface yang dapat diperluas untuk berbagai objek, sehingga kode menjadi lebih fleksibel, modular, dan mudah dikembangkan tanpa harus mengubah struktur yang sudah ada.


**REFLECTION TUTORIAL 4**
1. TDD yang digunakan dalam tutorial ini cukup membantu dalam mengarahkan proses pengembangan dengan memastikan pengujian dilakukan sebelum implementasi fitur. Hal ini membuat kode lebih terstruktur dan mempermudah deteksi bug sejak awal.
2. Test yang saya buat sudah memenuhi prinsip F.I.R.S.T., Test saya sudah memenuhi prinsip Fast, test yang saya buat sudah sedikit, namun mencakup semua method yang ada. Lalu, tes yang saya buat juga sudah independen antar fungsi dan tidak mengganggu fungsi lain. Lalu, test yang saya buat sudah memenuhi prinsip Thorough dimana saya membuat happy dan unhappy test. 

