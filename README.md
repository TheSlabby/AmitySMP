
# AmitySMP

A simple PaperMC plugin for a small survival world.
## Commands

```
/sethome & /home - home commands
/broadcast - sends discord webhook to URL in config.yml
/tpa - requests a teleports
/motd - sets the motd in AmitySMP.data file

/join - joins a team (requires leaderboard setup)
/stats - shows leaderboard stats (requires leaderboard setup)
```


## Build

Maven build (requires dependencies)

```bash
mvn assembly:assembly -DdescriptorId=jar-with-dependencies
```
## Authors

- [@TheSlabby](https://www.github.com/theslabby)

