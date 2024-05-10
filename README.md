# AppcentCaseStudyAyberkKoybasi
News App

İyi günler Appcent ekibi, ben Ayberk Köybaşı

uygulamanın yetenekleri: 
-Asenkron haber listeleme
-haber detayını görüntüleme
-uygulamadan çıkmadan haber kaynağını görüntüleyebilme
-haber linkini paylaşabilme
-haber favorileme ve favorileri görüntüleme (favorilerden de haber görüntülenebiliyor)
-dinamik arama fonksiyonu ile haberleri filtreleme

Kodun hızlı anlaşılabilmesi için kod içi yorum satırları yazdım. Burada da hangi class
ne iş yapar kısa açıklamaları aşağıdakilerdir:

1.paket: Adapter
NewsAdapter: Haber öğelerini bir RecyclerView içinde göstermek için kullanılır. Her haber öğesi için tıklama işlevselliği sağlar ve NewsDetailActivity'e yönlendirir.

2.paket: Controller
(object)FavoritesManager: Kullanıcının favori haberlerini yönetim işlemleri burada. Haberlerin favorilere eklenmesi, kaldırılması ve favori kontrolü bu sınıf üzerinden yapılır.

3.paket: Model
DataClasses: Haber verilerini temsil eden data sınıflarını içerir. Article, Newsresponse, Source data classlar bu pakette tanımlandı.

4.paket: Network
(interface)NewsService: Haberleri çekmek için kullanılan API metodlarını tanımlar.
(object)RetrofitClient: Retrofit kütüphanesi ile yapılandırılmış HTTP istemcisidir. NewsService'i kullanarak API'dan veri çeker.

5.paket: View
MainActivity: Kullanıcılara haber listesi gösterilir, arama yapılabilir.
FavoritesActivity: Kullanıcının favori haberlerini listeler.
NewsDetailActivity: Seçilen bir haberin detaylarını gösterir.
NewsSourceActivity: Haberin sayfasını gösteren bir web görünümü sağlar.(uygulamadan çıkmadan)

