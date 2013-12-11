Paddle ball example

To run from the repl
----
```
gamejam.core=>(load "paddle-ball")

```
You can close out the window and muck with it from the repl by typing
```
gamejam.core=>(in-ns 'gamejam.paddle-ball)

```

Pretty much all you could easily change is the title and size. 
```
gamejam.paddle-ball=>(defsketch foo :title "oh look I set the title on the window, herp derp" :setup setup :draw draw :size [500 500])

```

This is in no way how we want to actually write our game.  But its a working example as a reference of all the things required to get a basic sketch on the screen.

