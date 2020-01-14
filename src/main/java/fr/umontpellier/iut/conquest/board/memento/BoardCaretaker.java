package fr.umontpellier.iut.conquest.board.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public class BoardCaretaker {
    final Deque<BoardMemento> mementos = new ArrayDeque<>();

    public BoardMemento getMemento() {
        return mementos.pop();
    }

    public void addMemento(BoardMemento memento) {
        mementos.push(memento);
    }

    /**
     * @return
     * @see java.util.Collection#isEmpty()
     */

    public boolean isEmpty() {
        return mementos.isEmpty();
    }

    
}