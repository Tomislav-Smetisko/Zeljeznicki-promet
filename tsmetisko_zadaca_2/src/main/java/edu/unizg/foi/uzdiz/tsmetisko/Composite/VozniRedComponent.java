package edu.unizg.foi.uzdiz.tsmetisko.Composite;

public abstract class VozniRedComponent {
  public abstract void ispisi();

  public boolean add(VozniRedComponent component) {
    if (!(this instanceof VozniRedComponent)) {
      return false;
    }
    return this.add(component);
  }

  public boolean remove(VozniRedComponent component) {
    if (!(this instanceof VozniRedComponent)) {
      return false;
    }
    return this.remove(component);
  }

  public VozniRedComponent getChild(int i) {
    if (!(this instanceof VozniRedComponent)) {
      return null;
    }
    return this.getChild(i);
  }
}
