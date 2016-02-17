# Scyla

## Canvas engine for Android

Scyla is a rotation safe canvas engine for Android. Available on maven.
It's run inside a fragment so you can use it wherever you want !

## Add Dependency

<a href='http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22fr.nelaupe%22%20AND%20a%3A%22scyla%22'><img src='http://img.shields.io/maven-central/v/fr.nelaupe/scyla.svg'></a>

**Gradle dependency**

``` groovy
compile 'fr.nelaupe:scyla:1.1.2@aar'
```

**Maven dependency**

``` xml
<dependency>
    <groupId>fr.nelaupe</groupId>
    <artifactId>scyla</artifactId>
    <version>1.1.2</version>
</dependency>
```

## Sample
How simple is to create an "Hello word" that you can drag ?

``` java
public class myView extends ScylaCanvasView {
	
	public myView(ViewHandler handler, Context context) {
		super(handler, context);
	}

	@Override
	public void addCustomElements(Scene scene, GridTemplate grid) {
		final Text txt = new Text(context(), "Hello world", grid.getCenter());
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

}

```

## Applications that use Scyla

* [Paper Ball](https://play.google.com/store/apps/details?id=divingteam.divingball)

## Contributors

* [Lucas Nelaupe](http://www.lucas-nelaupe.fr/) - <https://github.com/lucas34>
* [Anthony Ferrand](http://www.anthony-ferrand.fr) - <https://github.com/crikka>

## License

Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)
