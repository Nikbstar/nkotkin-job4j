package ru.nik66;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> values = new SimpleArray<>(10);

    @Override
    public void add(T model) {
        this.values.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        if (this.findById(id) != null) {
            this.values.set(this.getIndexById(id), model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (this.findById(id) != null) {
            this.values.delete(this.getIndexById(id));
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result;
        try {
            result = this.values.get(this.getIndexById(id));
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            result = null;
        }
        return result;
    }

    private int getIndexById(String id) {
        int result = 0;
        for (T value : this.values) {
            if (value != null && value.getId().equals(id)) {
                break;
            }
            result++;
        }

        if (result >= this.values.size()) {
            result = -1;
        }

        return result;
    }


}
