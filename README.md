# mercari-android-test
Sample test app for Mercari.

## Libraries used
  - [Retrofit](https://square.github.io/retrofit/) as HTTP client
  - [Glide](https://github.com/bumptech/glide) for image downloaiding and display
  - [RxJava](https://github.com/ReactiveX/RxJava) to efficiently handle async network calls and employ reactive pattern
  - [Dagger](https://google.github.io/dagger/) for dependency injection
 
## Architecture used
  - [MVP](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) with interactor
  - There are many architectures available for application design: MVC, MVP, MVVC etc. I have used MVP as Presenter in MVP is 
    just an interface and not at all tied to the View. Due to this, MVP is quite suitable for unit and instrumentation testing 
    as well as it is more flexible compare to MVC.

I have experimented with other Image loading libraries: [Picasso](http://square.github.io/picasso/), [Volly](https://github.com/google/volley) and [Fresco](https://github.com/facebook/fresco), but [Glide](https://github.com/bumptech/glide) seems to be performing best amongst all due to it's well optimized Cache mechanism.

## Test
Basic Instrument tests are implemented to test RecycleView content.
