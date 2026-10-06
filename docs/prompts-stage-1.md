# Prompts Stage 1

Modelo usado en todos los prompts: `claude-sonnet-5-5` (Claude Sonnet 5.5, claude.ai).

Los mensajes que solo pegaban la salida de la terminal (errores de `gh`, `git push`, `git add` y listados de carpetas) no cambiaron archivos y no se incluyen en la tabla.

| Number | Model | Exact prompt text | Files changed |
|---|---|---|---|
| 1 | claude-sonnet-5-5 | en base a esto realizalo en 13 commits para poder subirlo antes de als 12 de la noche | ninguno (plan de commits, respuesta con guia; adjuntos: refactor.pdf y Tarea_1.txt) |
| 2 | claude-sonnet-5-5 | como es una tarea debo crear un repocitorio en git hub | ninguno (instrucciones para crear el repositorio) |
| 3 | claude-sonnet-5-5 | perdoname pero creo que subi esto asi :<br>git remote add origin https://github.com/TU_USUARIO/project-library-refactor.git<br> como agrego mi ususiario ? | ninguno (git remote set-url) |
| 4 | claude-sonnet-5-5 | este es el url https://github.com/Andoniprog/project-library-stage1-refactor | ninguno (git remote set-url) |
| 5 | claude-sonnet-5-5 | continuemos , con los demas commits | ninguno (rutas y creacion de archivos nuevos; el codigo base aun no estaba disponible) |
| 6 | claude-sonnet-5-5 | es una tarea no nos dieron el proyecto como tal | ninguno (se acordo conseguir o reconstruir el codigo base) |
| 7 | claude-sonnet-5-5 | (sin texto; se adjunto 03_architectures.zip con el codigo base del curso) | Los 13 commits del Stage 1: src/main/java/cl/ucn/disc/arqsist/library/service/NotFoundException.java, service/LoanPolicy.java, db/LocalDatePersister.java, model/Loan.java, model/Reservation.java, model/Book.java, model/Member.java, dao/BaseDao.java, dao/BookDao.java, dao/MemberDao.java, dao/LoanDao.java, dao/ReservationDao.java, service/BookService.java, service/MemberService.java, service/LoanService.java, service/ReservationService.java, db/Database.java, App.java, controller/*.java; src/test/.../DueDateDuplicationTest.java, TransactionBugTest.java; src/main/resources/public/app.js, public/index.html, logback.xml; docs/class-diagram.puml; .gitignore |
