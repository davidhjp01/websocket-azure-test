(ns clojure-websocket.core
  (:require [org.httpkit.server :as s]
            [environ.core :refer [env]]
            [taoensso.timbre :as timbre]
            [taoensso.timbre.appenders.core :as appenders])
  (:gen-class)
  (:import (java.util TimeZone)
           (java.time LocalDateTime)
           (java.time.format DateTimeFormatter)))

(defn handler [request]
  (if (:websocket? request)
    (s/with-channel request channel
      (s/on-close channel (fn [status] (println "channel closed: " status)))
      (s/on-receive channel (fn [data]                      ;; echo it back
                              (s/send! channel data))))
    {:status  200
     :headers {"Content-Type" "text/html"}
     :body    "hello HTTP!"}))

(defn log-file-name []
  (str
    "log-"
    (.format (DateTimeFormatter/ofPattern "yyyy_MM_dd")
      (LocalDateTime/now))
    ".log"))

(defn -main []
  (timbre/merge-config!
    {:appenders {:println (merge
                            (appenders/println-appender)
                            {:min-level (keyword "info")})
                 :spit    (merge
                            (appenders/spit-appender {:fname (str "C:/home/LogFiles/" (log-file-name))})
                            {:min-level (keyword "info")})}})
  (s/run-server handler {:port (Integer/valueOf (or (System/getenv "HTTP_PLATFORM_PORT") "8080"))}))