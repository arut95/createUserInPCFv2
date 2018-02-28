- -exc
- |

echo $(pwd)
echo $(ls -lart)

LAST_COMMIT_HASH=$(cd appsourcecode && git log -1 | grep commit | cut -d' ' -f2)

LAST_COMMIT_DETAILS=$(cd appsourcecode && git log -1 --name-status)

echo "paluser enablement deploy successful ${LAST_COMMIT_HASH}" >> email-content/subject.txt

echo "paluser enablement deploy successful\n\n ${LAST_COMMIT_DETAILS}" >> email-content/body.txt
