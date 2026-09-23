package lw01.unguided;

public abstract class WashService implements Billable {
    private final String id;
    private final int days;

    protected WashService(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be a positive number");
        }
        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("Units must be a positive number");
        }
        return units * calculateCharge();
    }

    public String label() {
        return "Service";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}

