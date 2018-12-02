# Vozilla

# 1) Architektura
Wybrana architektura do tego projektu to MVP + clean architecture oraz moja autorskie podejście do rozbicia obszaru "repository" na Feed, Service oraz RetrofitApi. Takie podejście zapewnia możliwość stworzenia odpowiednich komponentów takich jak:

View -> Presenter -> Interactor -> Feed -> Service -> RetrofitApi

Największa ilość testów jednostkowych powinna się zawrzeć w obiektach Prestner, Interactor oraz Feed (w przypadku gdy dokonujemy mapowania obiektów, tą czynnością może się też zająć sam interactor)

# 2) Architektura build variants
W aplikacji domyślnie zostały utworzone dwie wersje build variantów (debug, release). Dodałem do nich NetworkModule oraz RestUrl, w taki sposób na wersji produkcyjnej nie będą zapisywane logi z OkHttp. Dodatkowo pomyślałem że w przyszłości ktoś mógłby chcieć zmienić adres url do naszego serwera, więc spokojnie zrobi to w pliku RestUrl.

# 3) Biblioteki użyt do stworzenia aplikacji
- Dagger2
- BottomNavigation
- OkHttp
- Retrofit
- RxJava / RxAndroid
- Google maps
- Google maps helper
- Glide
- Joda time

Użyty język do napisania tej apliakcji to Kotlin (99% kodu)

# 4) Wygląd
Sam wygląd aplikacji jest dość prosty, składa się on z jednego Activity posiadającego cztery fragmenty (Wszystkie obiekty, Parkingi, Pojazdy oraz Strefy). Na potrzeby szybkiego stworzenia aplikacji stworzyłem cztery mapy zamaist jednej ogólnej, ponieważ też biblioteka mapy od Googla była na samym początku troche niezrozumiała dla mnie.

Samo filtrowanie obdywa się poprzez wybór odpowiedniej grupy obiektów na pasku nawigacyjnym. Dodatkowo zakładka pojazdy ma swoje proste filtorwanie do, którego dostęp mamy z actionbara. Na potrzeby zrobienia tego w miare szybko stworzyłem filtrowanie tylko po jednym elemencie, chociaż doskonale wiem że z samego api wynika iż można podać listę.

# 5) Pole do popraw
- Poprawa filtrowania na wiele elementów
- Poprawiona obsługa obrotów ekranu
- Poprawiona obsługa mapy (zamiast czterech)
