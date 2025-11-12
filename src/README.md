TODO
Add a config/ package later for CORS, Security, and Swagger.

Add .env or application-dev.yml to hide DB credentials if you deploy.

Add cascade delete rules (e.g., delete all messages when a match is deleted).

Add audit fields like createdBy (optional).

Add indexes (@Index) on frequently queried columns like user1_id, user2_id, match_id.

✅ 8. Security

Currently: default Spring Security generates a temporary password (you saw it in logs).
💡 Next step:

Implement JWT authentication:

AuthController

AuthService

Token filters (OncePerRequestFilter)

Secure APIs so that /users, /matches, /messages need an auth token.