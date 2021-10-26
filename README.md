# Minecraft Configuration

**This API is a simple method to create configuration**

There are many [examples](https://github.com/lorisdemicheli/MC-Config/tree/main/src/test/java/com/github/lorisdemicheli/loader/examples)

It's possible create a test plugin, download the repository and run maven build

#### What can it do Minecraft Configuration?

- Create configuration for own plugin only with a couple of annotations
- Save and Load very quickly

#### How does it work?

- Annotate Class with @Entity all class 
- Annotate Field with @Column
- Annotate Class with @YmlFile for set a class file
- If you don't want a field annotate with @IgnoreSave

#### Example Save and load

```java
class Main extends JavaPlugin {
    EntityManager em = new YamlLoader(this);
   
    PlayerType obj = new PlayerType();
    //... set value to obj
    
    em.save(obj);
    
    PlayerType saved = em.load(PlayerType.class,"uuid key");
}
```
#### Example Entity to save

```java
@Entity
@YmlFile(path = "friends")
public class PlayerType{
  @Id
  @Column
  private String uuid;
  @Column
  private String name;
  //... setter, getter and constructor
}
```
