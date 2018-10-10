Dijkstra Algoritması, graflar içinde birbirine bağlı düğümler arasında gidilebilen en kısa yolu bulmayı amaçlar.

Dijkstra algoritması sözde kodu:
o	Başlangıç noktasını belirle.
o	Başlangıç noktasından diğer noktalara olan maliyeti belirle ve düşük maliyetli noktayı işaretle.
o	İkinci adımda işaretlenen noktadan gidilebilen diğer noktalar arasında da aynı işlemi tekrarla.

Dijkstra algoritması kodu anlatımı:

ShortestPath class ı içinde final tanımlanmış V değeri yolun kaç değişken olduğunu tutan değerdir.

minuzaklik fonksiyonunda algoritmada bulunan değişkenlerin olası bütün yollara erişim için sonsuz değeri atanır. 
Daha sonra değerler alınarak kullanılacak mesafe değeri döndürülür.

printSolution fonksiyonu yapılandırılmış kısa mesafeyi yazdırmak için kullanılır.

dijkstra fonksiyonu bitişik matris için tek kaynağın en kısa yolunu bulur. Tanımlı dist dizisi çıkış dizisidir. 
Alınan değerin en kısa mesafesi bulunup bu diziye aktarılır ve yazdırmak için printSolution fonksiyonuna aktarılıp yazdırılır.

Uygulayıcı fonksiyonda üzerinde arama yapılacak grap, iki boyutlu dizi kullanarak oluşturulur. 
İki boyutlu graph dizisinin elemanları bütün elemanlara komşuluklarının belirlenmesi için tek tek elemanların girilmesi istenir. 
Oluşturulan dizi en dijkstra fonksiyonuna gönderilip en kısa mesafeler aranır.

