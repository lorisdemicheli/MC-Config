package com.github.lorisdemicheli.loader.examples.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.lorisdemicheli.loader.yaml.annotations.Entity;
import com.github.lorisdemicheli.loader.yaml.annotations.Column;
import com.github.lorisdemicheli.loader.yaml.annotations.Id;
import com.github.lorisdemicheli.loader.yaml.annotations.IgnoreSave;
import com.github.lorisdemicheli.loader.yaml.annotations.YmlFile;


@Entity
@YmlFile(multiple = true,path = "friends",useDefaultPath = false)
public class PlayerType{
	
	@IgnoreSave
	private Player player;
	@Id
	@Column
	private String uuid;
	@Id
	@Column
	private String name;
	@Column
	private boolean b;
	@Column
	private List<Entita> essere = new ArrayList<>();
	@Column(name = "PROVA_AMICI")
	private List<Amici> amici = new ArrayList<>();
	
	public PlayerType() {}
	
	public PlayerType(Player player) {
		this.player = player;
		this.uuid = player.getUniqueId().toString();
	}

	public Player getPlayer() {
		if(player == null) {
			player = Bukkit.getPlayer(UUID.fromString(uuid));
		}
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		this.uuid = player.getUniqueId().toString();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "PlayerType [player=" + player + ", uuid=" + uuid  + ", name=" + name + ", essere=" + essere + ", amici=" + amici + ", b=" + b + "]";
	}

	public List<Entita> getEssere() {
		return essere;
	}

	public void setEssere(List<Entita> essere) {
		this.essere = essere;
	}

	public List<Amici> getAmici() {
		return amici;
	}

	public void setAmici(List<Amici> amici) {
		this.amici = amici;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getB() {
		return b;
	}
	
	public void setB(boolean b) {
		this.b = b;
	}
}
