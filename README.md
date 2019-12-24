# yazilim-mimarisi-ve-tasarimi

## Yazılım Mimarisi Tasarım Ödevi

## Singleton Tasarım Deseni
Bu tasarım modelini internetten araştırdım,iyice kavradım ve edindiklerim şunlar;Öncelikle
singleton ingilizcede tek olan şeye  verilen isimdir.Bizim dilimizce de uyarlanışı
'tek nesne' denebilir.Kafamda tasarım deseni ile ilgili oluşan şu; Bir sınıftan
sadece bir nesne üretip bu nesneye globalca erişilebilir nokta sağlamak.Bir tane
nesne oluşturmamızdaki kasıt ise,herkesin istediği zaman bu sınıfın bir nesnesini
oluşturmaya çalışmamasıdır.'BİR SINIFIN SADECE BİR TANE NESNESİNİN OLDUĞUNDAN 
EMİN OL'.İle kastedilen budur.

...java
package com.BasarKoc;
public class Singleton {
Singleton nesne=new Singleton(); 
}
...
Singleton sınıfından nesne oluşturmanın ifadesi yukarıdaki gibidir.Biz bunu önüne nasıl geçecez 
bazı kısıtlamalar ile.

```java
package com.BasarKoc;
public class Singleton {
private static Singleton nesne=new Singleton();
private Singleton() {
System.out.println("Nesne Oluşturuluyor");
}
}
```
Yukarıda tanımladığımız private Constructoru(Yapıcı Fonksiyonumuz) hiç böyle görmemiştik.Genelde public
olurdu bu şekilde bu sınıfın nesnesini kimse oluşturamaz.Bunun doğrusu "Yukarıdaki Singleton sınıfının
nesnesini,Singleton sınıfı dışında kimse oluşturamaz".“nesne” ile referans verdiğimiz nesne, nesne değişkeni değil, sınıf değişkeni, yani “static” olmalıdır.

```java
package com.BasarKoc;
private static Singleton nesne=new Singleton();
public class Singleton {
    private Singleton() {
    System.out.println("Nesne Oluşturuluyor")
}
public static Singleton getInstance() {
    return nesne;
}
}
}
```
artık singleton sınıfımız gerçekten tasarım kalıbının istediği gibi tek bir nesnesi olan ve bu nesneye global erişim sağlayan yapıdadır.
``` java
package com.BasarKoc;
public class Singleton {
    private static Singleton singleton=new Singleton();
                        
    private static int count;
    private String name;
                        
    private Singleton() {
    count++;
    name=count+". Tasarım Nesnesi"
}
    public static Singleton getInstance() {
    return singleton;
}
    public void ismiYazdir() {
    System.out.println(name);
}                    
}
```
Yukarıdaki Singleton Sınıfını,sadece tek bir nesnesinin oluşturulduğunu ispat 
etmek için yaptık.Şimdi Ana Main kodundada görücez.
```java
package com.BasarKoc;
public class Main {
    public static void main(String[] args) {
    for(int i=0;i<10;i++) {
    Singleton s=Singleton.getInstance();
    s.ismiYazdir();
}
}
}
```
bunu ana mainde çalıştırırsak program konsola 10 kere 1. Tasarım Nesnesi yazdırcak
Çünkü sadece bir nesne oluşturulmuştur çünkü o tek nesnenin ismi '1.Tasarım Nesnesi'dir.


## Structural Pattern Bridge Yapısal  Deseni
Bridge(Köprü) tasarım deseni ise; yapısal tasarım desenlerinden birisidir.Soyutladığımız nesneler ile
gerçekleşecek somut nesneler arasında köprü kurar. Yani oluşturduğumuz interfacede ki methodları var olan
classlarımızda somut hale dönüştürerek kullanamak.Soyut sınıflar ve işi yapacak sınıfları birbirinden ayırdığı için iki sınıf tipinde yapılcak bir değişiklik birbirini etkilemez.

...java
package com.BasarKoc;
public class Singleton {
Singleton nesne=new Singleton(); 
}
...
Şimdi biz bu soyut sınıflar ile somut sınıflar arasındaki köprüyü oluşturacak kodları ele alalım.

```java
package com.BasarKoc;
public interface Color {
public void applyColor();
}
}
```
En ilk başta köprü olarak somut sınıflarla görev yapacak olan Color interfacemi oluşturdum ve içine bir geri dönüş tipi olmayan bir method tanımladım.

```java
package com.BasarKoc;
public abstract class Shape{
  protected Color color;    
   
  public Shape(Color c) {
    this.color=c;
}
    abstract public void applyColor();
}

```
Şimdi ikinci soyut sınıfımız abstract sınıfını oluşturdum.bir classın içinde abstract method varsa o class abstract olmak zorundadır.Abstract sınıfındaki abstract methodların gövdesi boş olur.Biz bu classın içinde Color interface tipinde bir color fieldı oluşturduk ve bunun constructorına Color tipinde değer alıp onu atayacaz.Ama şu vardır ki biz soyut sınıfardan NESNE ÜRETEMEYİZ.Şimdi nesne üretemeyeceğimiz için biz classlarımızdan extends ettiğimizde extends ettiğimiz sınıfın yapıcı fonksiyonu parametreli ise varolan classta da zorunlu aynı parametreli yapıcı fonksiyon oluşacak ve super olcağı için abstract sınıfın constructorunu kullancaz. İşte burda köprü görevi yapmış oluyor.
``` java
package com.BasarKoc;
public class Triangle extends Shape {
  public Triangle(Color c){
    super(c);
}
 @Override
 public void applyColor(){
    System.out.println("Triangle filled with color");
    color.applyColor();
}                                 
}
```
şimdi biz Triangle classımızı Shape abstract classımızdan extends ettiğimiz için içinde abstract olan methodumuz zorunlu olarak override biçimde geldi. köprü mantığıyla bi yakadan diğer yakaya geçen araba gibi düşünebiliriz.Köprü yardımıyla geçti. ve protected olarak Shape sınıfımızda ki color fieldımızla interfacemizdeki methodu çağırmış olduk
``` java
package com.BasarKoc;
public class Pentagon extends Shape {
    public Pentagon(Color c) {
    super(c);
}
    @Override
    public void applyColor(){
        System.out.println("Pentagon filled with color");
        color.applyColor();
}
}

```
Triangle classımız için yazdıklarımızın hepsi Pentagon classı içinde geçerlidir.

``` java
package com.BasarKoc;
public class RedColor implements Color {
    public void applyColor(){
    System.out.println("red");
}

}

```
Şimdi renk classlarımızı tanımladık.Kırmızı ve Yeşil renkli classlarımız. Biz bu classlarımızı interface ile köprü oluşuturup interfacede varolan methodumuza ulaştık.İmplement ettiğimizde interfacede method varsa varolan sınıfımızda oluşturmak zorundayız.
``` java
package com.BasarKoc;
public class GreenColor implements Color {
    public void applyColor(){
    System.out.println("Green");
}

}

```
Kırmızı renk sınıfımızdaki olaylar Yeşil sınıfımızdaki olaylar içinde geçerlidir.
``` java
package com.BasarKoc;
public class BridgePatternTest {
    public static void main(String[] args) {
    Shape tri=new Triangle(new RedColor());
    tri.applyColor();
    
    Shape pent=new Pentagon(new GreenColor());
    pent.applyColor();
}
  
}
```
Son olarak geldik anamainimize ve uygulamayı çalıştırmaya biz bu şekilde uygulamayı çalıştırırsak
"Triangle filled color with Red ve Pentagon filled color with Green" çıktısını alırız. Peki nasıl alırız ? şimdi biz bir taraf soyut bir taraf somut sınıftan nesne ürettik. Bu programlama dillerinde programa esneklik sağlar.Shape tri=new Triangel(new RedColor()); tri.applyColor(); dediğimizde shape tipinde bir tri nesnemiz var ve bu nesnemizin özelliklerini kullanırken Triangle sınıfımızdaki olayları kullanabiliriz.tri.applyColor(); dediğimizde Triangle sınıfımızdaki method çalışacak ve aynı sırada
Triangle sınıfımızdaki yapıcı fonksiyonumuz Color tipinde bir şey bekliyor biz Color a=new RedColor();
diyip a yı gönderebilirdik. sonuçta eşitliğin sol tarafıyla sağ tarafı eşittir. o yüzden direk new RedColor(); yaptık ve onu Color tipine yani bir taraf soyut bir taraf somut olcak şekilde atadık.
sonra Triangle sınıfımızdaki applyColor() methodumuz çalıştı ve bize Triangle filled with color red yazısını verdi. Trianlge nesnesi oluştururkende yapıcı fonksiyonunada RedColor nesnesi gönderdik ve oda aynı anda color.applyColor diyerek red Yazısını yazdırdı. Yani biz somut sınıflarımızı değiştirsek bile soyut sınıflarımız etkilenmeyeceğinden dolayı köprü olarak görev yapacağından bu yapısal desene bridge köprü adı konmuş. Umarım açıklayıcı olmuştur :)
