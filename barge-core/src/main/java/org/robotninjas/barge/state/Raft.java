package org.robotninjas.barge.state;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;
import org.robotninjas.barge.RaftException;
import org.robotninjas.barge.api.AppendEntries;
import org.robotninjas.barge.api.AppendEntriesResponse;
import org.robotninjas.barge.api.RequestVote;
import org.robotninjas.barge.api.RequestVoteResponse;


/**
 * Main interface to a Raft protocol instance.
 */
public interface Raft {

  public enum StateType {
    START, FOLLOWER, CANDIDATE, LEADER, STOPPED
  }

  void addRaftProtocolListener(RaftProtocolListener protocolListener);

  CompletableFuture<StateType> init();

  @Nonnull RequestVoteResponse requestVote(@Nonnull RequestVote request);

  @Nonnull AppendEntriesResponse appendEntries(@Nonnull AppendEntries request);

  @Nonnull
  CompletableFuture<Object> commitOperation(@Nonnull byte[] op) throws RaftException;

  void addTransitionListener(@Nonnull StateTransitionListener transitionListener);

  @Nonnull StateType type();

  void stop();
}
