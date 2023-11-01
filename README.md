# InventoryApi
[![CodeFactor](https://www.codefactor.io/repository/github/scynostv-dev/inventoryapi/badge/master)](https://www.codefactor.io/repository/github/scynostv-dev/inventoryapi/overview/master)

Inventory Gui Api for Minecraft 1.19 (paper)<br>
Java 16 - 18<br>
Unser Discord bei Fragen: https://discord.gg/qQd4qGA

## Erstelle dein eigenes Inventar-Gui für Minecraft!
## Einfache benutzung der API und Erklärungen
noch kein Wiki zu verfügung. Bitte auf den Discord kommen dann kann ich es dir erklären ^^<br>
Es sei denn du verstehst das ***"testplugin"***

## Maven Repository and Dependency
```xml
<!--Maven mit Github Login Token secret dingens (nicht wirklich geeignet für eine Produktion)-->

<repository>
    <id>inventoryApi</id>
    <url>https://raw.github.com/PolygonDev/InventoryApi/repository/</url>
</repository>

<dependency>
    <groupId>de.PolygonDev</groupId>
    <artifactId>inventoryapi</artifactId>
    <version>TAG</version>
    <scope>provided</scope>
</dependency>

<!--Jitpack erlaubt externe maven builds vom github Repository ohne Token secret kram-->

<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.PolygonDev</groupId>
    <artifactId>InventoryApi</artifactId>
    <version>TAG</version>
</dependency>

<!--TODO: Auto download from my plugins so you dont need to give 2 plugins to everyone
# NO CHANGES FOR 1.19.x!-->
```

Sollte es irgendwelche probleme mit Jitpack maven geben kannst du hier selbst einen commit / branch builden lassen<br>
Außerdem sollte dort auch stehen welche builds bereits verfügbar sind damit du es einfach kopieren kannst<br>
https://jitpack.io/#ScynostV/InventoryApi


## 1.19 Paper (bevorzugt) und Spigot kompaktibel
Die API ist in Paper geschrieben, allerdings kann man es auch problemlos auf Spigot benutzen.<br><br><br>
Das sind Bilder vom ***testplugin***:

<p align="center">
  <img src="https://user-images.githubusercontent.com/37050667/118070044-7e9b1300-b3a5-11eb-9d2e-72dadd5f6a85.png">
  <img src="https://user-images.githubusercontent.com/37050667/118070100-9a061e00-b3a5-11eb-9a68-f6b394ebb9e4.png">
  <img src="https://user-images.githubusercontent.com/37050667/118070205-c3bf4500-b3a5-11eb-844c-5a0878531cfe.png">
</p>

<!--![grafik](https://user-images.githubusercontent.com/37050667/118070044-7e9b1300-b3a5-11eb-9d2e-72dadd5f6a85.png)
![grafik](https://user-images.githubusercontent.com/37050667/118070100-9a061e00-b3a5-11eb-9a68-f6b394ebb9e4.png)
![grafik](https://user-images.githubusercontent.com/37050667/118070205-c3bf4500-b3a5-11eb-844c-5a0878531cfe.png)-->
