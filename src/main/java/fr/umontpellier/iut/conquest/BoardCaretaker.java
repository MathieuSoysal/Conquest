package fr.umontpellier.iut.conquest;

import java.util.ArrayDeque;
import java.util.Deque;

public class BoardCaretaker {
    final Deque<BoardMemento> mementos = new ArrayDeque<>();

    public BoardMemento getMemento() {
        BoardMemento memento = mementos.pop();
        return memento;
    }

    public void addMemento(BoardMemento memento) {
        mementos.push(memento);
    }
}