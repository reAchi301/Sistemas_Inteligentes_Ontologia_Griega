@# DSI_KS_FINAL_Orfeo_Myth_Makers (Drools project)

## How to run
Run `mitologia.Main` (Run As > Java Application). It reads every `escenarios/EscenarioF2-x.txt`
and writes `soluciones/RespuestaF2_x.txt`.

## Structure
- `src/main/java/mitologia/modelo/` shared fact classes (one class per ontology concept, one class per relation/slot)
- `src/main/java/mitologia/LectorEscenario.java` parser: each txt line becomes ONE basic fact (no inference in Java)
- `src/main/java/mitologia/Main.java` runs every scenario with the agendas `inicial` -> `mitos` -> `respuesta`
- `src/main/resources/reglas/base.drl` default knowledge + generic rules shared by every myth (DON'T duplicate them)
- `src/main/resources/reglas/andromeda.drl` F2-1 specific rules
- `lib/slf4j-simple-1.7.26.jar` logger (added to the build path with "Add JARs", relative path)

## Adding your scenario
1. Create `escenarios/EscenarioF2-x.txt` (no accents, no n with tilde, no final period).
2. Create your own `src/main/resources/reglas/<your_myth>.drl` with `package reglas` and `import mitologia.modelo.*`.
   Put your rules in `agenda-group "mitos"`.
3. If you need a new character/object/default owner, add it to the rule "Conocimiento inicial por defecto" in `base.drl`.
4. If you need a NEW fact type, tell Ricardo before creating it so the model stays shared.
5. Rule consequences (`then`) only insert / modify / delete facts + `insert(new Suceso("..."))` for the story line.

## Lines the parser understands
- `Puede X liberar|matar|apresar a Y?` / `Puede X obtener O?` / `Puede X tener la capacidad de C?`
- `X tiene el favor de D` / `X tiene el enojo de D` / `X tiene la capacidad de C`
- `X tiene O` / `X no tiene O` / `X localiza a Y` / `X es preso de Y` / `X es hijo de Y`
Multi-word names are joined: "Escudo de Bronce" -> EscudoBronce, "las Ninfas" -> Ninfas.
@