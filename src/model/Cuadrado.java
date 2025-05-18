@Override
public boolean contiene(int x, int y) {
    int half = lado / 2;
    return x >= posicion.x - half && x <= posicion.x + half &&
           y >= posicion.y - half && y <= posicion.y + half;
}
