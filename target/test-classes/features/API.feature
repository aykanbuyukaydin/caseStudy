@wip
Feature: US_002 Crud Islemleri

  Scenario: TC_2001 Bütün Stok ve Fiyat Bilgisini Okuma
    Then Kullanici butun stok ve fiyat bilgisini okumak icin "http://mockservice.com/allGrocery" endpointe gider
    And Status kodunun 200 oldugunu ve gelen response degerlerini kontrol eder
      |id |name  |price |stock |
      |1  |apple |3     |100   |
      |2  |grapes|5     |50    |

  Scenario: TC_2002 İsme Göre Stok ve Fiyat Bilgisini Okuma
    Then Kullanici stok ve fiyat bilgisini okumak icin "http://mockservice.com/allGrocery/apple" endpointe gider
    And Status kodunun 200 oldugunu ve gelen response degerini kontrol eder
      |id |name  |price |stock |
      |1  |apple |3     |100   |

  Scenario: TC_2003 İsme Göre Stok ve Fiyat Bilgisini Okuma
    Then Kullanici stok ve fiyat bilgisini okumak icin "http://mockservice.com/allGrocery/banana" endpointe gider
    And Status kodunun 404 oldugunu kontrol eder

  Scenario: TC_2004 Yeni Meyve yada Sebze ekleme
    Then Kullanici meyve yada sebze eklemek icin "http://mockservice.com/add" endpointe gider
      |id |name  |price |stock |
      |4  |string|12.3  |3     |
    And Status kodunun 200 oldugunu ve meyve yada sebzenin eklemdigini kontrol eder

  Scenario: TC_2005 Yeni Meyve yada Sebze ekleme
    Then Kullanici meyve yada sebze eklemek icin "http://mockservice.com/add" endpointe gider
      |id |name  |price |stock |
      |1  |apple |3     |50    |
    And Status kodunun 400 oldugunu kontrol eder