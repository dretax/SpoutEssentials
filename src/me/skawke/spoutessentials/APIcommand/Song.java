package me.skawke.spoutessentials.APIcommand;

import me.skawke.spoutessentials.SpoutEssentials;



public class Song{

	/**
	 * 
	 */
	private String url;
	private static int duration;
	public SpoutEssentials plugin;
	
	public Song(String url, int duration){
		this.url = url;
		Song.duration = duration;
	}
	
	public Song(SpoutEssentials instance)
	{
		this.plugin = instance;
	}
	
	
	/**
	 * @return the duration
	 */
	public static int getDuration() {
		return duration;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	@Override
	public String toString(){
		return duration + " " + url.replace(" ", "%20");
	}
	
	/**
	 * @param s duration + " " + url
	 * @return Song built from the string.
	 */
	public static Song buildFromString(String s){
		return new Song (s.split(" ")[1], Integer.parseInt(s.split(" ")[0]));
	}
}
