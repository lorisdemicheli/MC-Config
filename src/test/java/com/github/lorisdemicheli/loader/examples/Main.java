package com.github.lorisdemicheli.loader.examples;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.lorisdemicheli.loader.EntityManager;
import com.github.lorisdemicheli.loader.examples.entity.Amici;
import com.github.lorisdemicheli.loader.examples.entity.Config;
import com.github.lorisdemicheli.loader.examples.entity.Entita;
import com.github.lorisdemicheli.loader.examples.entity.PlayerType;
import com.github.lorisdemicheli.loader.yaml.YamlLoader;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		PlayerType pt = new PlayerType();
		pt.setUuid("UUUUUUUUUUUID");
		pt.setName("xLoris99");
		Entita e  = new Entita("CANE", "Maschio");
		e.getSopprannomi().add("PIPPO");
		e.getSopprannomi().add("Pluto");
		Entita e2  = new Entita("GATTO", "FEMMINA");
		e2.getSopprannomi().add("PANNA");
		e2.getSopprannomi().add("NERINO");
		pt.getEssere().add(e);
		pt.getEssere().add(e2);
		Amici a = new Amici(32, true);
		pt.getAmici().add(a);
		
		Config c = new Config(false,"pippo","password");
		
		EntityManager em = new YamlLoader(this);
		
		em.save(pt);
		em.save(c);
		
		
		PlayerType ptr = em.load(PlayerType.class,"UUUUUUUUUUUID","xLoris99");
		Config cr = em.load(Config.class);
		System.out.println("-----------------------------------------");
		System.out.println(ptr.toString());
		System.out.println("-----------------------------------------");
		System.out.println(cr.toString());
	}
}
