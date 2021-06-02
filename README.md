# Minecraft Configuration

**This API is a simple method to create configuration**

There are many [examples](https://github.com/lorisdemicheli/MC-Config/tree/main/src/test/java/com/github/lorisdemicheli/loader/examples)

It's possible create a test plugin, download the repository and run maven build

What can it do Minecraft Configuration?
-

- Create configuration for own plugin only with a couple of annotations
- Save and Load very quickly

How does it work?
-

- Annotate Class with @Entity all class 
- Annotate Field with @Column
- Annotate Class with @YmlFile for set a class file
- If you don't want a field annotate with @IgnoreSave
