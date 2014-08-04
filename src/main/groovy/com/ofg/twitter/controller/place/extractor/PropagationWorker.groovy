package com.ofg.twitter.controller.place.extractor

interface PropagationWorker {
    void collectAndPropagate(long pairId, String tweets)
}