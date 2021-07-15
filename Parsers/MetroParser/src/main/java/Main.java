import java.io.IOException;


/*
1. 3 станции не парсятся ( у них кривые теги)
2. вместо 8а идет 8.5
3. солнцевская линия разбита на 2 части
4. вместо 11А идет 11.5
5. Document doc = Jsoup.connect(link).maxBodySize(0).get();
Настройка билдера снимающая лимит с объема выкачки страницы, вот так качнет полностью
6. Юзай библиотеку gson. Там все просто и быстро разберешься
7. в функцию toGson кидай объект, содержащий и станции, и линии, и пересечения(мультиобъект)
8. Читай код spb metro.
 */
public class Main {



  public static void main(String[] args) throws IOException {



    //"content-wrapper"

    System.out.println("9. имя станции".replaceAll("[\\D.]",""));


//    Document document = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines")
//        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
//        .maxBodySize(0)
//        .get()
//        ;
////<span class="js-metro-line t-metrostation-list-header t-icon-metroln ln-1" data-line="1">Сокольническая линия</span>
//    Elements el = document.select("div.js-depend p");
//    System.out.println("________________________");
//    el.forEach(System.out::println);
//    System.out.println("________________________");

//   // Elements el = document.select("div.js-depend span.t-icon-metroln ");
////
//    //Elements linesNames = document.select("span.js-metro-line");
//
//    Elements linesNames = document.select("div.js-depend p");
//    System.out.println(linesNames.size());
//    //linesNames.stream().map(Element::text).collect(Collectors.toList()).forEach(System.out::println);
//    String delimiter2 = linesNames.stream().map(Element::text).filter(string -> !string.matches("\\d+.\\s[аА-яЯ]+")).findFirst().orElse("S о линии");
//    System.out.println(delimiter2);
//
//    System.out.println(!"Подробно о линии".matches("\\d+.\\s[аА-яЯ]+"));



//"div#metrodata"

//   Elements elements =
//       strings.stream().map(Element::new).collect(Collectors.toCollection(Elements::new));
//elements.forEach(System.out::println);


    // String s = "/1. Бульвар Рокоссовского/2. Черкизовская/3. Преображенская площадь";

    //System.out.println(s.replaceAll("[\\w.]",""));// убирает все кроме букв

    //linesNames.stream().map(Element::text).collect(Collectors.toList()).forEach(System.out::println);





    //linesNames.forEach(i-> System.out.println(i));






    // <span class="t-icon-metroln ln-14" title="переход на станцию «Бульвар Рокоссовского» МЦК"></span>




//    List<String> lines = List.of(
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-1\" data-line=\"1\">Сокольническая линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-2\" data-line=\"2\">Замоскворецкая линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-3\" data-line=\"3\">Арбатско-Покровская линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-4\" data-line=\"4\">Филевская линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-5\" data-line=\"5\">Кольцевая линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-6\" data-line=\"6\">Калужско-Рижская линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-7\" data-line=\"7\">Таганско-Краснопресненская линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-8\" data-line=\"8\">Калининско-Солнцевская линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-9\" data-line=\"9\">Серпуховско-Тимирязевская линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-10\" data-line=\"10\">Люблинско-Дмитровская линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-11\" data-line=\"11\">Большая Кольцевая Линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-11A\" data-line=\"11A\">Каховская линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-12\" data-line=\"12\">Бутовская линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-14\" data-line=\"14\">Центральное Кольцо</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-15\" data-line=\"15\">Некрасовская линия</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-D1\" data-line=\"D1\">МЦД-1</span>",
//        "<span class=\"js-metro-line t-metrostation-list-header t-icon-metroln ln-D2\" data-line=\"D2\">МЦД-2</span>"
//    );
//    MetroParser metroParser = new MetroParser(null);
//
//
//   //lines.forEach(i -> System.out.println(i.substring(i.indexOf("line=")+6,i.indexOf("line=")+9).replaceAll("[\",>]","")));
//   //lines.forEach(i -> System.out.println(i.substring(i.indexOf("line="))));
//   // System.out.println("line=\"".length());



/**
 * <div class="js-metro-stations t-metrostation-list-table" data-line="1" style="grid-template-rows: repeat(13,36px);">
 *   <p><a data-metrost="1,32,1,10,43,0" href="/metro/sokolnicheskaya-linija/bulvar-rokossovskogo.html"><span class="num">1.&nbsp;&nbsp;</span><span class="name">Бульвар Рокоссовского</span><span class="t-icon-metroln ln-14" title="переход на станцию «Бульвар Рокоссовского» МЦК"></span></a></p>
 *   <p><a data-metrost="2,31,0,20,311,0" href="/metro/sokolnicheskaya-linija/cherkizovskaya.html"><span class="num">2.&nbsp;&nbsp;</span><span class="name">Черкизовская</span><span class="t-icon-metroln ln-14" title="переход на станцию «Локомотив» МЦК"></span></a></p>
 *   <p><a data-metrost="3,30,0,30,220,0" href="/metro/sokolnicheskaya-linija/preobrajenskaya-ploshchad.html"><span class="num">3.&nbsp;&nbsp;</span><span class="name">Преображенская площадь</span></a></p>
 *   <p><a data-metrost="4,29,0,40,257,0" href="/metro/sokolnicheskaya-linija/sokolniki.html"><span class="num">4.&nbsp;&nbsp;</span><span class="name">Сокольники</span></a></p>
 *   <p><a data-metrost="5,28,0,50,110,0" href="/metro/sokolnicheskaya-linija/krasnoselskaya.html"><span class="num">5.&nbsp;&nbsp;</span><span class="name">Красносельская</span></a></p>
 *   <p><a data-metrost="6,12,1,60,101,0" href="/metro/sokolnicheskaya-linija/komsomolskaya.html"><span class="num">6.&nbsp;&nbsp;</span><span class="name">Комсомольская</span><span class="t-icon-metroln ln-5" title="переход на станцию «Комсомольская» Кольцевой линии"></span><span class="t-icon-metroln ln-D2" title="переход на станцию «Каланчёвская» Курско-Рижского (второго) диаметра"></span></a></p>
 *   <p><a data-metrost="7,26,0,70,111,0" href="/metro/sokolnicheskaya-linija/krasnyye-vorota.html"><span class="num">7.&nbsp;&nbsp;</span><span class="name">Красные ворота</span></a></p>
 *   <p><a data-metrost="8,25,0,80,314,0" href="/metro/sokolnicheskaya-linija/chistyye-prudy.html"><span class="num">8.&nbsp;&nbsp;</span><span class="name">Чистые пруды</span><span class="t-icon-metroln ln-10" title="переход на станцию «Сретенский бульвар» Люблинско-Дмитровской линии"></span><span class="t-icon-metroln ln-6" title="переход на станцию «Тургеневская» Калужско-Рижской линии"></span></a></p>
 *   <p><a data-metrost="9,24,0,90,139,0" href="/metro/sokolnicheskaya-linija/lubyanka.html"><span class="num">9.&nbsp;&nbsp;</span><span class="name">Лубянка</span><span class="t-icon-metroln ln-7" title="переход на станцию «Кузнецкий мост» Таганско-Краснопресненской линии"></span></a></p>
 *   <p><a data-metrost="10,23,0,100,192,0" href="/metro/sokolnicheskaya-linija/okhotny-ryad.html"><span class="num">10.&nbsp;</span><span class="name">Охотный ряд</span><span class="t-icon-metroln ln-3" title="переход на станцию «Площадь Революции» Арбатско-Покровской линии"></span><span class="t-icon-metroln ln-2" title="переход на станцию «Театральная» Замоскворецкой линии"></span></a></p>
 *   <p><a data-metrost="11,22,0,110,32,0" href="/metro/sokolnicheskaya-linija/biblioteka-imeni-lenina.html"><span class="num">11.&nbsp;</span><span class="name">Библиотека имени Ленина</span><span class="t-icon-metroln ln-9" title="переход на станцию «Боровицкая» Серпуховско-Тимирязевской линии"></span><span class="t-icon-metroln ln-4" title="переход на станцию «Александровский сад» Филевской линии"></span><span class="t-icon-metroln ln-3" title="переход на станцию «Арбатская» Арбатско-Покровской линии"></span></a></p>
 *   <p><a data-metrost="12,33,0,120,115,0" href="/metro/sokolnicheskaya-linija/kropotkinskaya.html"><span class="num">12.&nbsp;</span><span class="name">Кропоткинская</span></a></p>
 *   <p><a data-metrost="13,21,1,130,197,0" href="/metro/sokolnicheskaya-linija/park-kultury.html"><span class="num">13.&nbsp;</span><span class="name">Парк Культуры</span><span class="t-icon-metroln ln-5" title="переход на станцию «Парк Культуры» Кольцевой линии"></span></a></p>
 *   <p><a data-metrost="14,20,0,140,302,0" href="/metro/sokolnicheskaya-linija/frunzenskaya.html"><span class="num">14.&nbsp;</span><span class="name">Фрунзенская</span></a></p>
 *   <p><a data-metrost="15,19,0,150,260,0" href="/metro/sokolnicheskaya-linija/sportivnaya.html"><span class="num">15.&nbsp;</span><span class="name">Спортивная</span><span class="t-icon-metroln ln-14" title="переход на станцию «Лужники» МЦК"></span></a></p>
 *   <p><a data-metrost="16,18,0,160,61,0" href="/metro/sokolnicheskaya-linija/vorobyevy-gory.html"><span class="num">16.&nbsp;</span><span class="name">Воробьевы горы</span></a></p>
 *   <p><a data-metrost="17,17,0,170,296,0" href="/metro/sokolnicheskaya-linija/universitet.html"><span class="num">17.&nbsp;</span><span class="name">Университет</span></a></p>
 *   <p><a data-metrost="18,16,0,180,223,0" href="/metro/sokolnicheskaya-linija/prospekt-vernadskogo.html"><span class="num">18.&nbsp;</span><span class="name">Проспект Вернадского</span></a></p>
 *   <p><a data-metrost="19,15,0,190,330,0" href="/metro/sokolnicheskaya-linija/yugo-zapadnaya.html"><span class="num">19.&nbsp;</span><span class="name">Юго-Западная</span></a></p>
 *   <p><a data-metrost="196,220,0,200,283,0" href="/metro/sokolnicheskaya-linija/troparyovo.html"><span class="num">20.&nbsp;</span><span class="name">Тропарёво</span></a></p>
 *   <p><a data-metrost="199,223,0,210,237,0" href="/metro/sokolnicheskaya-linija/rumyancevo.html"><span class="num">21.&nbsp;</span><span class="name">Румянцево</span></a></p>
 *   <p><a data-metrost="200,222,0,220,242,0" href="/metro/sokolnicheskaya-linija/salaryevo.html"><span class="num">22.&nbsp;</span><span class="name">Саларьево</span></a></p>
 *   <p><a data-metrost="260,272,0,230,297,0" href="/metro/sokolnicheskaya-linija/filatov-lug.html"><span class="num">23.&nbsp;</span><span class="name">Филатов Луг</span></a></p>
 *   <p><a data-metrost="261,273,0,240,221,0" href="/metro/sokolnicheskaya-linija/prokshino.html"><span class="num">24.&nbsp;</span><span class="name">Прокшино</span></a></p>
 *   <p><a data-metrost="262,274,0,250,187,0" href="/metro/sokolnicheskaya-linija/olhovaya.html"><span class="num">25.&nbsp;</span><span class="name">Ольховая</span></a></p>
 *   <p><a data-metrost="263,275,0,260,100,0" href="/metro/sokolnicheskaya-linija/kommunarka.html"><span class="num">26.&nbsp;</span><span class="name">Коммунарка</span></a></p>
 *  </div>
 */


    /*
    //названия линий
    Elements linesNames = document.select("span.js-metro-line");
    linesNames.stream().map(Element::text).collect(Collectors.toList()).forEach(System.out::println);
     */

     /*
    //список станций
    Elements linesNames = document.select("div.js-depend p");
    linesNames.stream().map(Element::text).collect(Collectors.toList()).forEach(System.out::println);
     */



  }

}
/*
 <div class="js-toggle-depend s-depend-control-single  s-depend-control-active" data-depend="{'toggle-slide':'lines-1'}">
   <span class="js-metro-line t-metrostation-list-header t-icon-metroln ln-1" data-line="1">Сокольническая линия</span>
  </div>
  <div class="js-depend s-depend-active" data-depend-set="lines-1">
   <p class="t-metrostation-list-headerlink"><a href="/metro/sokolnicheskaya-linija.html">Подробно о линии</a></p>
   <div class="js-metro-stations t-metrostation-list-table" data-line="1" style="grid-template-rows: repeat(13,36px);">

   <div class="js-toggle-depend s-depend-control-single  " data-depend="{'toggle-slide':'lines-2'}">
   <span class="js-metro-line t-metrostation-list-header t-icon-metroln ln-2" data-line="2">Замоскворецкая линия</span>
  </div>
  <div class="js-depend" data-depend-set="lines-2">
   <p class="t-metrostation-list-headerlink"><a href="/metro/zamoskvoretskaya-linija.html">Подробно о линии</a></p>
   <div class="js-metro-stations t-metrostation-list-table" data-line="2" style="grid-template-rows: repeat(12,36px);">

   <div class="js-toggle-depend s-depend-control-single
*/
