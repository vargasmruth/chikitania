package com.chikitania.tasks.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;

/**
 * Created by Ruth on 23/09/2019.
 */
 
public class DatabaseOperation {

  /**
   * This method executes database transactions with hibernate.
   *
   * @param sessionFactory {@link SessionFactory}
   * @param mode {@link DbAccessMode}
   * @param action {@link Runnable}
   */
  public static void exec(SessionFactory sessionFactory, DbAccessMode mode, Runnable action) {
    try (Session session = sessionFactory.openSession()) {
      ManagedSessionContext.bind(session);
      switch (mode) {
        case WRITE:
          writeAction(action, session);
          break;
        default:
          readAction(action);
          break;
      }
    } finally {
      ManagedSessionContext.unbind(sessionFactory);
    }
  }

  private static void writeAction(Runnable action, Session session) {
    Transaction transaction = session.beginTransaction();
    try {
      action.run();
      transaction.commit();
    } catch (Exception e) {
      transaction.rollback();
      throw new RuntimeException(e);
    }
  }

  private static void readAction(Runnable action) {
    try {
      action.run();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
