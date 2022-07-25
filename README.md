# STAR CAST

**This application is demo of start cast in which you can search people on home page and get details after you tap on it.**

<img width="722" alt="Screenshot 2022-07-25 at 8 33 46 PM" src="https://user-images.githubusercontent.com/28764106/180810148-25d100f8-f52b-4de6-a5c9-4519721f0d1d.png">



###### Third Party's in Star Cast Android

```
 

 1. Retrofit
 2. Navigation Component
 3. Coroutines
 4. Koin
 
```


###### MVVM Architecture

```
I create this application in mvvm architecture in which two views are there 
1. HomeScreenFragment (UI Layer)
2. CharacterDetailFragment (UI Layer)


1.1 CharacterSearchViewModel (ViewModel)

1.2 NetworkBuilder (Retrofit Init)

1.3 Api (Retrofit Interface) Where we write end point of api


```


###### First Screen (HomeScreenFragment)

```
 
In Home Screen we used search bar on top of the screen which is first screen of navigation graph you get from nav_graph file in this screen you search people ex - Owen Lars 

After search people list will show with all names on recycler view ... when you tap on that name it navigate to next screen.
 
```



###### Second Screen (CharacterDetailFragment)


```
 In Second Screen you will see the deatils of star people and getting information of opening crawl with the help of film's key api's.
```


