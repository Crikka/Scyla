# Scyla

## Canvas engine for Android


### Add Dependency

``` java
compile 'fr.nelaupe:scyla:1.0.1@aar'
```

Create your own view
This sample code show "Hello word" that you can drag

``` java

public class myView extends ScylaCanvasView {
	
	public myView(ViewHandler handler, Context context) {
		super(handler, context);
	}

	@Override
	public void addCustomElements(Scene scene, GridTemplate grid) {
		final Text txt = new Text(context(),"Hello world",grid.getCenter());
		scene.addStaticElement(txt);
		
		this.subscribeTouchListener(txt,new TouchEvent() {
			@Override
			public void onTouch(Point point, TouchTypeEvent event) {
				if(event == TouchTypeEvent.Move){
					txt.gravityCenterFacet().moveGravityCenterTo(point);
				}
			}
		});
	}
	
	@Override
	public void onBackPressed() {
	
	}

}

```


### Last Updated

Release beta

## Applications that use Scyla

* [Paper Ball](https://play.google.com/store/apps/details?id=divingteam.divingball)

## Contributors

* [Lucas Nelaupe](http://www.lucas-nelaupe.fr/) - <https://github.com/lucas34>
* [Anthony Ferrand](http://www.anthony-ferrand.fr) - <https://github.com/crikka>

## License

Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)
