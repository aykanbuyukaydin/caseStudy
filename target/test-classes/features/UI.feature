@wip
Feature: US_001 CaseStudy Islemleri

  Background: ortak step adimlari
    Given Kullanici "n11" sitesine gider

  Scenario: TC_1001 Login Logout
    And Kullanici Giris Yap sekmesine tiklar
    And Kullanici "eposta" ve "sifre" bilgilerini girer
    And Kullanici Giris Yap butonuna tiklar
    Then Login olup "TT" geldigini dogrular
    When Hesabim menusunun uzerine gelir
    And "Çıkış Yap" dropdown secer
    Then "Giriş Yap" sekmesinin geldigini dogrular
    Then Kullanici sayfayi kapatir

  Scenario: TC_1002 Hatali Login Deneme
    And Kullanici Giris Yap sekmesine tiklar
    And Kullanici "eposta" ve "hataliSifre" bilgilerini girer
    And Kullanici Giris Yap butonuna tiklar
    Then "E-posta adresiniz veya şifreniz hatalı" mesaji gorur
    And Hata mesajini "loginerror.txt" dosyasina yazdirir
    Then Kullanici sayfayi kapatir

  Scenario: TC_1003 Search Keyword
    Then Kullanici "iphone" icin arama islemi yapar
    And Arama sonuclarini goruntuler
    And Arama sonuclarini "results.txt" dosyasina kaydeder
    And Arama sonuclarinin ekran goruntusunu alir
    Then Kullanici "mjdsbfmdsbvhmsdv" icin arama islemi yapar
    And Arama sonuclarinin olmadigini goruntuler
    And Arama sonuclarinin olmadigi "results.txt" dosyasina kaydeder
    And Arama sonuclarinin ekran goruntusunu alir
    Then Kullanici sayfayi kapatir

  Scenario: TC_1004 Parameterize Login
    And Kullanici Giris Yap sekmesine tiklar
    And Kullanici "Username" ve "Password" bilgilerini "config.txt" dosyasindan okur
    And Kullanici Giris Yap butonuna tiklar
    Then Login olup "TT" geldigini dogrular
    When Hesabim menusunun uzerine gelir
    And "Çıkış Yap" dropdown secer
    Then "Giriş Yap" sekmesinin geldigini dogrular
    Then Kullanici sayfayi kapatir

  Scenario: TC_1005 Parameterize Search
    Then Kullanici "SearchTerm" icin parametreleri "config.txt" dosyasindan okur
    And Arama sonuclarini goruntuler
    Then Kullanici sayfayi kapatir



#burda ekran goruntusu, dosya acma yazdırma vs gibi islemler ayrı bir yerde method olarak yapılıp steplerde direk method da cagrilabilirdi
    # burda 3 maddeyi ayrıca tek feature degil 3 feature olarak yapıp a ve b seneceklerini ise test case1 ve test case2 olarak da yapabilirdik
    # en basta tets datası icin kullanıcı adı ve sifre de olusturabilirdik ben direk dataları yazdım
  # mimariyi daha da dinamik yapmak icin degisken(veriable) sayısını da arttırılabilir



