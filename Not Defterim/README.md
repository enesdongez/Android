#Not Defterim

 Bu uygulama kullanıcılara güvenlikli giriş imkânıyla notlarını korumayı ve girilen notların uygulamayı 
kullanan diğer kullanıcılara gösterebildikleri bir uygulamadır. Bu uygulama sayesinde hem kendiniz için hem de diğer
kullanıcılar için notlar hazırlayabilirsiniz, bu sayede kullanıcılar içerisinde iletişim daha sağlıklı ve daha 
kontrollü bir şekilde sağlanmış olur.

Uygulamamızın genel yapısından bahsetmek gerekirse, uygulamamızda o anki kullanıcının daha önceden girdiği notları 
tekrar görebilmekte ve girilmiş olan notları düzenleyip tekrar kaydedebilmektedir. Kullanıcılar uygulama içerisine
girdiği yeni bir nota alarm ekleyebilmektedir, bununla birlikte alarm ekleme bölünden notlardan bağımsız bir alarm
da ekleyebilmektedir. Bu alarm girilen saat aralığı dolduktan sonra kullanıcının telefonuna o anki telefon mesaj 
sesine göre bildirim vermektedir, bildirimin içeriği ise kullanıcı tarafından belirlenmektedir.
Kullanıcılar uygulama içerisinden yeni bir not girerken bunu diğer kullanıcılara gösterebildikleri gibi sadece
kendilerinin okuyabildikleri bir ortamda da saklayabilmektedir, bunun için not ekleme bölünde bulunan combobax’ın 
işaretli olması yeterlidir. Herkesin görebildiği bir not paylaştığınızda, bu notlara paylaşılmış notlar bölünden 
erişebilirsiniz ve erişilen notları inceleyip kim tarafından paylaşıldığını da görebilirsiniz. Uygulama içerisinde 
girilen notları, whatsapp üzerinden -girilen başlık ve içerik- olarak tanımlanan notu alıp istediğimiz kullanıcıya 
gönderebilmekteyiz.

Projemizi kullanıcıların interaktif olarak kullanabilmesi için uygulamamız bir web servisten veri almaktadır, 
bu sayede uygulama içerinde bir değişiklik olduğunda bu diğer kullanıcıları da etkileyebilmektedir. Web servis 
üzerinden aldığımız veriler JSON formatında alıp bu verileri ihtiyacımıza göre dönüştürüp kullandık. Kullanıcıların 
gönderdiği servis isteklerine göre o servis çağırılıp işlendi ve kullanıcıya istediği dönüt sunuldu. Projemiz 
localde çalışır halde ancak bir server a servisimiz kuruldu ve test edildi, internete bağlı olan her yerden giriş
yapılabilir ve uygulama içerisindeki verilere erişim sağlanabilir. Uygulamamız genel olarak kullanıcıların 
saklamak istediği bir notu kayıt altına alıp daha sonra bakma imkânı sağladığı gibi bunu diğer kullanıcılarında
kendi notları için yapmalarını sağlamaktadır.
