# Czym jest REST ?

>Representational State Transfer (REST, ang. zmiana stanu poprzez reprezentacje) – styl architektury oprogramowania wywiedziony z doświadczeń przy pisaniu specyfikacji protokołu HTTP 
dla systemów rozproszonych. REST wykorzystuje m.in. jednorodny interfejs, bezstanową komunikację, zasoby, reprezentacje, hipermedia, HATEOAS. - wiki

### Richardson maturity model

 Wzorową praktyką projektowania serwisów typu REST jest model opracowany przez Leonarda Richardsona. 
 
Model ten zakłada podział na 0, 1, 2 i 3 poziomy. Im więcej z nich spełnia aplikacja, tym bardziej przyjazne jest API. 
Trzeba pamiętać, że jest to jedynie ważny standard, z którego należy korzystać. Tak, aby każdy Deweloper mógł sprawnie odnaleźć się w projekcie.
 
#### Poziom 0

Poziom 0 mówi o tym, że aplikacja REST jest oparta na protokole HTTP. Komunikacja z REST API odbywa się poprzez wysyłanie żądań i uzyskiwanie odpowiedzi. Analogicznie jak w przypadku pobierania zadań dostępnych w bazie danych.

#### Poziom 1

Aplikacje operują na zasobach. Przetwarzają, warunkują i udostępnią zadeklarowane zasoby. W praktyce wszystkie służą do pobierania, modyfikowania i zapisywania zasobów. 
Poziom 1 mówi o nadawaniu odpowiedniej hierarchii zasobom aplikacji. Kluczem jest odpowiednie nazywanie adresów URL do zasobów, tak aby było to zrozumiałe i czyste.

#### Poziom 2

Poziom drugi mówi o tym, aby korzystać z dostępnych metod protokołu HTTP takich jak GET, POST, PUT, DELETE. Wówczas zyska schematyczność i zgodność z dobrymi praktykami.
Metody HTTP służą do celów programistycznych. Dzięki nim wiemy, do czego służy dany endpoint. Przydatne są również w tworzeniu dokumentacji.

#### Poziom 3

Jest to poziom, którego właściwe zastosowanie skupia się na wskazaniu kierunku do innych endpointów aplikacji. 
Tak, aby umożliwiała pobieranie zasobów, które udostępnia poprzez API.

---
Back to the [ main project ](https://github.com/jszlenk/Rest-Tasks-Application)