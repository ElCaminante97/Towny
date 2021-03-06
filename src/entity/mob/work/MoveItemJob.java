package entity.mob.work;

import entity.item.Item;
import entity.mob.Villager;

public class MoveItemJob extends Job {
	private boolean pickUpJob; // is the job a pickup or drop job

	public MoveItemJob(Item material, Villager worker) {
		this(worker);
		pickUpJob = true;
		this.material = material;
		if (this.worker.isHolding(this.material) || !this.material.isReserved(this.worker)) {
			completed = true;
		} else {
			this.material.setReserved(this.worker);
			xloc = material.getX();
			yloc = material.getY();
		}
	}

	private MoveItemJob(Villager worker) {
		super(worker);
	}

	public MoveItemJob(int xloc, int yloc, Villager worker) {
		this(worker);
		pickUpJob = false;
		this.xloc = xloc;
		this.yloc = yloc;

	}

	@Override
	protected void start() {
		started = true;
		if (!pickUpJob) {
			if (worker.getHolding() == null || (!worker.level.isClearTile(xloc / 16, yloc / 16)
					&& !worker.level.itemAlreadyThere(xloc, yloc, worker.getHolding()))) {
				completed = true;
			}
			return;
		}
		worker.setMovement(worker.getPath(xloc / 16, yloc / 16));
		if (worker.isMovementNull()) {
			completed = true;
			if (pickUpJob) {
				material.removeReserved();
			}

		}
	}

	public void execute() {
		if (!completed && started) {
			if (pickUpJob) {
				if (worker.isHolding(material)) {
					completed = true;
					return;
				} else {
					if (worker.onSpot(material.getX(), material.getY())) {
						if (worker.pickUp(material)) {
							completed = true;
						}
						return;
					} else {
						if (worker.isMovementNull()) {
							if (worker.getPath(material) != null) {
								worker.setMovement(worker.getPath(material));
								return;
							} else {
								completed = true;
								return;
							}
						} else {
							worker.move();
						}
					}
				}
			} else {
				if (worker.onSpot(xloc, yloc)) {
					worker.drop();
					completed = true;
					return;
				} else {
					if (worker.isMovementNull()) {
						if (worker.getPath(xloc / 16, yloc / 16) != null) {
							worker.setMovement(worker.getPath(xloc / 16, yloc / 16));
							return;
						} else {
							worker.drop();
							completed = true;
							return;
						}
					} else {
						worker.move();
					}
				}
			}

		} else {
			start();
		}
	}

}
