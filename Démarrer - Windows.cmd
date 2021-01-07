@echo off
if exist out\artifacts\JavaDestructChess_jar\JavaDestructChess.jar (
    echo Pour un meilleur résultat, utilisé le nouveau terminal windows
    java -jar out\artifacts\JavaDestructChess_jar\JavaDestructChess.jar
) else (
    echo Veuillez générer le .jar d'abord
)

pause