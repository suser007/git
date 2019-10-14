package com.git.git;

/***
 *
 *    GitHub：远程代码托管中心
 *    git每个客户端也是一台服务器，
 *      仓库里面操作的都是元数据
 *      git的blog是共享的（最牛逼的）
 *      多一个文件就多一个blog对象
 *      多一个文件夹就多一个tree对象还多一个顶级tree对象
 *      多一个提交就多一个commit对象
 *  Untracked adv 未追踪状态
 *  No commits yet ：尚未提交
 *  Changes to be committed ：要提交的更改
 *  Git:分布式版本控制
 *                   add                             commit                                push                                     clone                              checkout
 *     workspace  ===========>  localCache(暂存区) ==========>localRepository(本地仓库)===============>remoteRepository(远程仓库)=============>localRepository(本地仓库)==========>branch workspace(分支工作空间)
 *                              产生blog对象                   产生commit对象                                                                                                                            git checkout 文件名（.所有文件）
 *                               存文件内容                          tree对象
 *      $find .get/objects/ -type f 查看对象目录
 *      $git cat-file -t a09fed43d96c589b1   查看对象类型
 *      $git cat-file -p a09fed43d96c589b1   查看对象内容
 *
 *      blog     内容
 *      $ git cat-file -p  3d67a68f5f
 *                                      blog:   dwadwad
 *
 *      commit   元数据
 *      $ git cat-file -p  c9a8e4774ba
 *                                      commit: tree 0b311a7d4fcc47b84390e9eea62da4ee80cc18be
 *                                              author susur <18995636120@163.com> 1570354804 +0800
 *                                              committer susur <18995636120@163.com> 1570354804 +0800
 *      tree     目录结构
 *      $ git cat-file -p  0b311a7d4fcc47
 *                                      tree:   040000 tree 98d82a90ec20b7401873c93206aeedf5f11ec5fd    sss
 *                                              100644 blob 3d67a68f5f6e4bfdd474952262fa970fc8fe62ad    susus.txt
 *
 *      .git\refs\heads\master
 *                                      master: commit
 *       在git里面 一个commit-id就相当于一个文件
 *       $git checkout commit-id HEAD直接指向commit-id
 *      .git\HEAD  当前本地工作空间对应的分支  内容为commit-id  当前分支指向的当前文件
 *      .git\refs\heads  本地分支
 *      .git\refs\remotes 远程分支
 *      为电脑配置身份信息
 *      $git config --global user.name "Your Name"
 *      $git config --global user.email "email@example.com"
 *
 *
 *      $git init           创建Git版本库，将某个目录变成git可以管理到的目录
 *      $git add            放到缓存区
 *      $git commit         提交到仓库
 *      $git diff           工作空间和暂存区的区别
 *      $git diff --cached
 *      $git diff HEAD          工作区和最新的仓库
 *
 *      $git log                可以查询commit-id
 *
 *      $git diff commit-id     工作区和commit-id
 *      $git diff --cached commit-id
 *      $git diff --commit-id commit-id
 *
 *
 *      $git log 查看修改日志以及注释
 *      $git log --pretty==oneline  查看每一条日志的第一行
 *
 *      $git reset --hard HEAD^     全部回滚到上一个版本
 *      $git reset --hard HEAD^^    全部回滚到上上个版本
 *      $git reset --hard HEAD~n    全部回滚n个版本
 *      $git reset --hard commit-id 回滚到commit-id版本
 *      $git reflog     查看git历史命令
 *
 *      $ssh-keygen -t rsa -C "email" 生成密钥
 *
 *
 *      echo "# susur" >> README.md
 *      git init
 *      git add README.md
 *      git commit -m "first commit"
 *      git remote add origin git@github.com:suser007/susur.git         给远程仓库取别名
 *      git push -u origin master           上传到远程仓库
 *
 *
 *
 *      分支：
 *      $git checkout  分支名                  切换分支
 *      $git branch  分支名                    创建分支
 *      $git branch  新分支  分支              基于分支创建新分支
 *      $git branch    -a                     所有的
 *                     -v                     信息
 *                     -v                     关联信息
 *                     -d xxx                 删除分支
 *
 *
 *
 *       **********************************************
 *       ******************分之合并*********************
 *
 *      克隆下来的项目需要更改，当前分支p1                   p1->p2->p1->上传
 *      $git branch  p2  创建p2分支
 *      $git checkout  p2   切换p2   工作ing              p1 ------------------p1-----------------------
 *      $git checkout  p1   切换p1
 *      $git merge p2    合并p2                           ----------p2---------------------------------
 *      $git push -u origin p1  上传p1
 *
 *
 *
 *      $git init -bare        在linux上创建ssh服务用于搭建远程仓库
 *
 *
 */
public class GitDemo {
}
