# FittrackPro - Team Responsibility Matrix

## Team of 4 - Equal Work Distribution

### **Person 1: User Management & Authentication**
**Responsible for:** User account system and security

**Files to work on:**
- `entity/User.java` - User model
- `entity/Role.java` - Role definitions
- `controller/AuthPageController.java` - Login/Register pages
- `repository/UserRepository.java` - Database queries
- `service/UserService.java` - Business logic
- `security/` - Authentication & authorization config
- `dto/RegistrationForm.java` - Registration forms
- `dto/ProfileUpdateForm.java` - Profile updates
- `templates/login.html`
- `templates/register.html`
- `templates/profile.html`

---

### **Person 2: Workout Management**
**Responsible for:** Workout tracking and logging

**Files to work on:**
- `entity/Workout.java` - Workout model
- `controller/WorkoutController.java` - Workout endpoints
- `repository/WorkoutRepository.java` - Database queries
- `service/WorkoutService.java` - Business logic
- Related frontend components for workout features

---

### **Person 3: Meal Management**
**Responsible for:** Meal logging and nutrition tracking

**Files to work on:**
- `entity/Meal.java` - Meal model
- `controller/MealController.java` - Meal endpoints
- `repository/MealRepository.java` - Database queries
- `service/MealService.java` - Business logic
- `dto/MealSummaryDTO.java` - Meal data transfer
- Related frontend components for meal features

---

### **Person 4: Admin Dashboard & Core Infrastructure**
**Responsible for:** Dashboard, monitoring, and system setup

**Files to work on:**
- `entity/AuditLog.java` - Audit tracking
- `controller/DashboardController.java` - Dashboard features
- `repository/AuditLogRepository.java` - Audit queries
- `service/AuditLogService.java` - Audit logic
- `config/DataInitializer.java` - Database setup
- `FittrackproApplication.java` - Main app config
- `templates/admin-dashboard.html`
- `templates/admin-profile.html`
- `templates/dashboard.html`
- `pom.xml` - Dependencies (coordinate with others)

---

## How to Work on GitHub

### Step 1: Initial Setup (Do this ONCE as the project owner)
```bash
cd e:\12th Projects\APA\fittrackpro
git init
git add .
git commit -m "Initial commit: FittrackPro project structure"
git remote add origin https://github.com/YOUR_USERNAME/fittrackpro.git
git branch -M main
git push -u origin main
```

### Step 2: Each Team Member's Workflow
Each person should create a **feature branch** for their module:

```bash
# Person 1 (User Management)
git checkout -b feature/user-management

# Person 2 (Workout)
git checkout -b feature/workout-management

# Person 3 (Meals)
git checkout -b feature/meal-management

# Person 4 (Admin/Dashboard)
git checkout -b feature/admin-dashboard
```

### Step 3: Daily Work Cycle
```bash
# Before starting work
git pull origin main

# Make your changes to YOUR assigned files
# ... edit files ...

# Commit frequently with clear messages
git add .
git commit -m "Add user registration endpoint"
git commit -m "Add meal entry form validation"

# Push to your branch
git push origin YOUR_BRANCH_NAME
```

### Step 4: Create Pull Request (When feature is ready)
On GitHub:
1. Go to Pull Requests tab
2. Click "New Pull Request"
3. Select `main` as base, your feature branch as compare
4. Add description of what you did
5. Other team members review and approve
6. Merge to main

---

## GitHub Best Practices for Your Team

✅ **DO:**
- Commit often (daily or multiple times per day)
- Use clear commit messages: `"Add user registration"` not `"fix stuff"`
- Push your branch daily so others can see progress
- Create a PR when your feature is complete/reviewable
- Use feature branch names that describe your work

❌ **DON'T:**
- Work directly on `main` branch
- Commit without messages
- Push broken code (test locally first)
- Make commits claiming others' work

---

## Viewing Team Contributions

To see who contributed what on GitHub:
1. Go to your repo → **Insights** → **Contributors**
2. Each person's commits will show separately with their GitHub account
3. **Network graph** shows your branch structure and merges

---

## Resolving Conflicts

If two people edit the same file:
```bash
git pull origin main  # See the conflict
# Edit the conflicted file to keep both changes
git add .
git commit -m "Resolve merge conflict in UserService"
git push origin your-branch
```

