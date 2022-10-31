(defproject clojure-websocket "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [http-kit "2.6.0"]
                 [environ "1.1.0"]
                 [com.taoensso/timbre "5.2.1"]
                 [com.fzakaria/slf4j-timbre "0.3.21"]]
  :main clojure-websocket.core
  :profiles {:uberjar      {:aot          :all
                            :main         clojure-websocket.core
                            :uberjar-name "app.jar"}})
