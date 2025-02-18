**REFLECTION 1**

Dalam mengerjakan tutorial dan exercise 1 ini, saya telah menerapkan clean code.
1. Meaningful names, saya telah membuat nama variabel dan fungsi yang deskriptif dan jelas sesuai dengan fungsinya. Contohnya: updatedName, artinya nama product yang sudah diupdate. findById(), yaitu fungsi untuk menemukan product dari productRepository menggunakan id.
2. Function, saya membuat beberapa function baru untuk membantu sebuah method daripada membuat semua kode itu (yang ada di function baru) dalam method tersebut. Contohnya function set pada ProductRepository
3. Comment, saya membuat comment yang jelas untuk beberapa fungsi yang agak panjang (tidak dapat langsung dimengerti dari sekilas) namun saya tidak membuat comment yang tidak diperlukan untuk nama variabel yang sudah jelas dan untuk fungsi yang sudah jelas kegunaannya.

Improvement di source code:
1. Membuat variabel idIterator untuk id dari product
2. Mengubah template untuk form create product


** REFLECTION 2**
1. Seteleh mengerjakan unit test, saya menyadari ada beberapa kesalahan minor dalam kode saya. Semisal, pada saat create product baru, saya tidak mempertimbangkan jika product sudah memiliki id, sebelumnya saya langsung membuatkan id menggunakan iterator untuk product itu. Banyaknya unit test yang dibutuhkan setiap class bergantung pada berapa banyak skenario yang mungkin untuk class itu. Untuk memastikan jika unit test kita cukup, kita harus bisa mempertimbangkan semua skenario yang dapat menyebabkan kode kita gagal. Jika kita memiliki 100% code coverage, bukan berarti code kita tidak memiliki bug. Kode yang perlu diuji di unit test hanyalah kode yang perlu saja, semisal sebuah fungsi yang trivial seperti penjumlahan dua angka tidak perlu kita lakukan unit test. Karena itu, code coverage yang baik adalah yang mencakup yang diperlukan.
2. Dengan setup yang sama, maka kode yang dibuat di awalnya kurang lebih akan sama, ini akan mengganggu prinsip clean code. Untuk mengatasinya, kita dapat membuat sebuah abstract class yang akan diinherit oleh kedua class CreateProductFunctionalTest.java dan java class yang baru dibuat.