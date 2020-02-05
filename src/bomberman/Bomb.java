package bomberman;

public class Bomb {
	private float x, y;
	private int start;
	private int time;
	private int range;
	private int player;
	private Map map;
	public boolean started = false;
	private boolean exploded = false;
	public boolean cleared = false;
	
	public Bomb(float x, float y, int time, int range, Map map, int player) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.range = range;
		this.map = map;
		this.player = player;
	}

	public void start() {
		this.map.poserBomb(this.x, this.y);
		this.started = true;
		this.start = MapGameState.TIME/1000;
	}
	
	public void update(int actualTime) {
		if (actualTime/1000 >= this.start + this.time && exploded == false) {
			this.explode();
			this.exploded = true;
		}
		if (actualTime/1000 >= (this.start+1) + this.time && exploded == true) {
			this.clear();
		}
	}
	
	public void explode() {
		this.map.exploserBomb(this.x, this.y, this.range, this.player);
	}
	
	public void clear() {
		this.map.clearExplosion(this.x, this.y, this.range);
		this.cleared = true;
		MapGameState.giveOneBomb(this.player);
	}
}