(ns avout.refs.local
  (:use avout.state)
  (:require [zookeeper :as zk]
            [avout.transaction :as tx]
            [avout.util :as util]))


(deftype LocalVersionedStateContainer [client name state]

  VersionedStateContainer

  (initVersionedStateContainer [this] nil)

  (destroyVersionedStateContainer [this] (reset! state {}))

  (getStateAt [this version]
    (get @state version))

  (setStateAt [this value version]
    (swap! state assoc version value))

  (deleteStateAt [this version]
    (swap! state dissoc version)))
